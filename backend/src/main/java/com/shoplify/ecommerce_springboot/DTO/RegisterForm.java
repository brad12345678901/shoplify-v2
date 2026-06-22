package com.shoplify.ecommerce_springboot.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record RegisterForm(
        @NotNull(message="Provide your First Name")
        String firstName,
        String middleName,
        @NotBlank(message="Provide your Last Name")
        String lastName,
        @NotBlank(message="Provide an Email")
        String email,
        @NotBlank(message="Provide a password")
        @Size(min = 8)
        String password
) {
}
