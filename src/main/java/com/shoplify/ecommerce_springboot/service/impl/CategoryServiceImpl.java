package com.shoplify.ecommerce_springboot.service.impl;

import com.shoplify.ecommerce_springboot.exception.ResourceNotFoundException;
import com.shoplify.ecommerce_springboot.entity.Category;
import com.shoplify.ecommerce_springboot.repository.CategoryRepository;
import com.shoplify.ecommerce_springboot.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shoplify.ecommerce_springboot.DTO.CategoryForm;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository db;

    public CategoryServiceImpl(CategoryRepository db) {
        this.db = db;
    }

    public List<Category> findAllCategories () {
        return db.findAll();
    }

    public Category getCategory(long id) {
        return db.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category ID "+id+" was not found"));
    }

    @Transactional
    public Category addCategory(CategoryForm dto) {
        Category newCategory = new Category();

        newCategory.setName(dto.name());

        return db.save(newCategory);
    }
}
