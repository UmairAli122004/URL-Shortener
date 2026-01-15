package com.UrlShortener.DTOs;

public record CreateShortUrlCmd(
        String originalUrl,
        Boolean isPrivate,
        Integer expirationIbDays,
        Long userId
        ) {
}