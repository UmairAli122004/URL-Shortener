package com.UrlShortener.exceptions;

public class ShortUrlNotFoundException extends RuntimeException {
    public ShortUrlNotFoundException(String message) {
        super(message);
    }
}
