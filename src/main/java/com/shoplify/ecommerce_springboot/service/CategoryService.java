package com.shoplify.ecommerce_springboot.service;

import com.shoplify.ecommerce_springboot.entity.Category;
import java.util.List;
import com.shoplify.ecommerce_springboot.DTO.CategoryForm;

public interface CategoryService {
    public List<Category> findAllCategories ();
    public Category getCategory(long id);
    public Category addCategory(CategoryForm dto);
}
