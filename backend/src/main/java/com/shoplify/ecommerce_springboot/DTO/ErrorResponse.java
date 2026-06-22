package com.shoplify.ecommerce_springboot.DTO;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record ErrorResponse(
        int status,
        boolean success,
        String message,
        long timestamp,
        Map<String, String> errors
) {
    public ErrorResponse(int status, boolean success, String message, long timestamp){
        this(status, success, message, timestamp, null);
    }
}
