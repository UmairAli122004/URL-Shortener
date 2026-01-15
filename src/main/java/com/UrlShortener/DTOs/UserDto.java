package com.UrlShortener.DTOs;

import java.io.Serializable;

public record UserDto(Long id, String name) implements Serializable {
}
