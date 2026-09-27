package com.example.fleet_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequest(
        @NotBlank @Size(max = 120) String fullName,
        @NotBlank @Email @Size(max = 254) String email,
        @NotBlank @Size(max = 40) String phone,
        @NotBlank @Pattern(regexp = "FLEET_MANAGER|MAINTENANCE_STAFF|FINANCE|ADMIN") String role,
        @NotBlank @Size(min = 8, max = 72) String password) {
}