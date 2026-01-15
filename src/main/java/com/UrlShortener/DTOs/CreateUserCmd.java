package com.UrlShortener.DTOs;

import com.UrlShortener.Enums.Role;

public record CreateUserCmd(
        String email,
        String password,
        String name,
        Role role) {
}