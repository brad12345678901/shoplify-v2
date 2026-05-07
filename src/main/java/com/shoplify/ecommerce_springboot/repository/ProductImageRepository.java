package com.shoplify.ecommerce_springboot.repository;

import com.shoplify.ecommerce_springboot.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImages, Long> {
}
