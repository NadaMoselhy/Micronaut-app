package com.example.exception;

import java.time.Instant;

public record ApiError(
        String errorCode,
        String message,
        String path,
        Object details,
        Instant timestamp
) {
    public static ApiError of(String code, String message, String path, Object details) {
        return new ApiError(code, message, path, details, Instant.now());
    }
}
