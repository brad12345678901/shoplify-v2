package com.shoplify.ecommerce_springboot.DTO;

import com.shoplify.ecommerce_springboot.model.Product;

import java.util.List;

public record ProductResourceList (
        long product_id,
        String name,
        String type,
        String description,
        double price,
        int stock,
        long category_id,
        String category_name,
        List<String> imageURL
) {
}
