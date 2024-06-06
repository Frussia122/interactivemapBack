package com.example.apiWithDb.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private final HttpStatus statusCode;
    public AppException(String message, HttpStatus code) {
        super(message);
        this.statusCode = code;
    }

    public HttpStatus getCode() {
        return statusCode;
    }
}
