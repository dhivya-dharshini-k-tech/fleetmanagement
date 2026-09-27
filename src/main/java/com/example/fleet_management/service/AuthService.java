package com.example.fleet_management.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HexFormat;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.fleet_management.dto.AuthLoginRequest;
import com.example.fleet_management.dto.AuthRegisterRequest;
import com.example.fleet_management.dto.AuthResponse;
import com.example.fleet_management.entity.ApiSession;
import com.example.fleet_management.entity.UserAccount;
import com.example.fleet_management.repository.ApiSessionRepository;
import com.example.fleet_management.repository.UserAccountRepository;

@Service
public class AuthService {

    private static final int SESSION_LENGTH_DAYS = 7;

    private final UserAccountRepository userAccountRepository;
    private final ApiSessionRepository apiSessionRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SecureRandom secureRandom = new SecureRandom();

    public AuthService(UserAccountRepository userAccountRepository, ApiSessionRepository apiSessionRepository) {
        this.userAccountRepository = userAccountRepository;
        this.apiSessionRepository = apiSessionRepository;
    }

    @Transactional
    public AuthResponse register(AuthRegisterRequest request) {
        String email = request.email().trim().toLowerCase();
        if (userAccountRepository.existsByEmailIgnoreCase(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An account with this email already exists.");
        }

        UserAccount user = new UserAccount();
        user.setFullName(request.fullName().trim());
        user.setEmail(email);
        user.setPhone(request.phone().trim());
        user.setRole(request.role());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        userAccountRepository.save(user);
        return profile(user, null);
    }

    @Transactional
    public AuthResponse login(AuthLoginRequest request) {
        UserAccount user = userAccountRepository.findByEmailIgnoreCase(request.email().trim())
                .filter(account -> passwordEncoder.matches(request.password(), account.getPasswordHash()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email or password is incorrect."));

        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        String accessToken = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);

        ApiSession session = new ApiSession();
        session.setTokenHash(hashToken(accessToken));
        session.setExpiresAt(Instant.now().plus(SESSION_LENGTH_DAYS, ChronoUnit.DAYS));
        session.setUserAccount(user);
        apiSessionRepository.save(session);
        return profile(user, accessToken);
    }

    @Transactional(readOnly = true)
    public boolean isTokenValid(String accessToken) {
        if (accessToken == null || accessToken.isBlank()) {
            return false;
        }
        return apiSessionRepository.findByTokenHashAndExpiresAtAfter(hashToken(accessToken), Instant.now()).isPresent();
    }

    @Transactional
    public void logout(String accessToken) {
        if (accessToken != null && !accessToken.isBlank()) {
            apiSessionRepository.deleteByTokenHash(hashToken(accessToken));
        }
    }

    private AuthResponse profile(UserAccount user, String accessToken) {
        return new AuthResponse(accessToken, user.getFullName(), user.getEmail(), user.getRole());
    }

    private String hashToken(String token) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (java.security.NoSuchAlgorithmException exception) {
            throw new IllegalStateException("Unable to process session token.", exception);
        }
    }
}