package com.shoplify.ecommerce_springboot.DTO;

public record UserDTO(
        long id,
        String firstName,
        String middleName,
        String lastName,
        String fullName,
        String email
) {
}
