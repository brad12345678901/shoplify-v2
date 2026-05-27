package com.shoplify.ecommerce_springboot.repository;

import com.shoplify.ecommerce_springboot.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
