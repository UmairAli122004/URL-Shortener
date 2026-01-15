package com.UrlShortener.DTOs;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ShortUrlDto(Long id, String shortKey, String originalUrl,
                          Boolean isPrivate, LocalDateTime createdAt,
                          UserDto createdBy, Long clickCount,
                          LocalDateTime expiresAt) implements Serializable {
}
