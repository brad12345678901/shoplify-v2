package com.shoplify.ecommerce_springboot.DTO;

public record APIResponse<T>(
        int status,
        boolean success,
        String message,
        T data
) {
}
