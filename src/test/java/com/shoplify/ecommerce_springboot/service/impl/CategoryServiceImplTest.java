package com.shoplify.ecommerce_springboot.service.impl;

import com.shoplify.ecommerce_springboot.DTO.CategoryForm;
import com.shoplify.ecommerce_springboot.entity.Category;
import com.shoplify.ecommerce_springboot.entity.Product;
import com.shoplify.ecommerce_springboot.entity.ProductImage;
import com.shoplify.ecommerce_springboot.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CategoryServiceImpl Unit Tests")
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    // DTO Form used to create Category
    private CategoryForm categoryForm;

    // List of Category
    private List<Category> category;

    // New Category
    private Category newCategory;

    @BeforeEach
    void setUp () {
        this.category = new ArrayList<>();
        this.category.add(new Category(1L, "Electronics", null));
        this.category.add(new Category(2L, "Men's Apparel", null));
        this.category.add(new Category(3L, "Women's Apparel", null));

        this.categoryForm = new CategoryForm("Appliances");
        this.newCategory = new Category(1L, "Appliances", null);
    }

    @Test
    void shouldReturnAllCategories() {
        //Given
        when(categoryRepository.findAll())
                .thenReturn(category);

        //When
        List<Category> result = categoryService.findAllCategories();

        // THEN
        verify(categoryRepository).findAll();

        assertEquals(3, result.size());

        assertAll("List of Category Assertions",
            () -> assertEquals(3, category.size(), () -> "Expected 3, Got "+category.size()+" instead."),
            () -> assertEquals("Electronics", result.getFirst().getName(), () -> "Expected \"Electronics\", got \""+result.getFirst().getName()+"\" instead"),
            () -> assertEquals("Men's Apparel", result.get(1).getName(), () -> "Expected \"Men's Apparel\", got \""+result.get(1).getName()+"\" instead"),
            () -> assertEquals("Women's Apparel", result.get(2).getName(), () -> "Expected \"Women's Apparel\", got \""+result.get(2).getName()+"\" instead")
        );
    }

    @Test
    void shouldCreateNewCategory() {
        //Given
        when(categoryRepository.save(any(Category.class))).thenReturn(newCategory);

        // When
        Category result = categoryService.addCategory(categoryForm);
        // Then
        verify(categoryRepository).save(any(Category.class));

        assertNotNull(result, () -> "There should be a returned Entity upon creation");
        assertEquals("Appliances", result.getName(), () -> "Expected \"Appliances\", got \""+ result.getName() +"\" instead");
    }
}