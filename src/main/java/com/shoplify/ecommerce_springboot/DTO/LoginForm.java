package com.shoplify.ecommerce_springboot.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginForm (
        @NotBlank(message="Email shouldn't be empty")
        @Email
        String email,
        @NotBlank(message="Enter a password")
        String password
) {
}
