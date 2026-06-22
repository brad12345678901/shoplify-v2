package com.shoplify.ecommerce_springboot.repository;

import com.shoplify.ecommerce_springboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
