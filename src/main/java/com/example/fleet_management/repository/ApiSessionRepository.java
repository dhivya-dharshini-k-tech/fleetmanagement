package com.example.fleet_management.repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.ApiSession;

public interface ApiSessionRepository extends JpaRepository<ApiSession, Long> {
    Optional<ApiSession> findByTokenHashAndExpiresAtAfter(String tokenHash, Instant now);

    void deleteByTokenHash(String tokenHash);
}