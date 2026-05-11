package com.shoplify.ecommerce_springboot.controller;

import com.shoplify.ecommerce_springboot.DTO.APIResponse;
import com.shoplify.ecommerce_springboot.DTO.CategoryForm;
import com.shoplify.ecommerce_springboot.model.Category;
import com.shoplify.ecommerce_springboot.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http//localhost:5173")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Category>>> listCategory () {
        List<Category> categories = categoryService.findAllCategories();

        APIResponse<List<Category>> response = new APIResponse<>(
                HttpStatus.OK.value(),
                true,
                "Categories fetched Successfully",
                categories
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<APIResponse<Category>> addCategory (@Valid @RequestBody CategoryForm dto) {
        Category newCategory = categoryService.addCategory(dto);

        APIResponse<Category> response = new APIResponse<>(
                HttpStatus.CREATED.value(),
                true,
                "Category fetched Successfully",
                newCategory
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
