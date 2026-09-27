package com.example.fleet_management.dto;

public record AuthResponse(
        String accessToken,
        String name,
        String email,
        String role) {
}