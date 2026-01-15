package com.UrlShortener.Controller;

import com.UrlShortener.exceptions.ShortUrlNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ShortUrlNotFoundException.class)
    String handleShortUrlNotFoundException(ShortUrlNotFoundException exception){
        log.error("Short URL not found : {}", exception.getMessage());
        return "error/404";
    }
}
