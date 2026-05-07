package com.shoplify.ecommerce_springboot.service.impl;

import DTO.ProductForm;
import com.shoplify.ecommerce_springboot.exception.ResourceNotFoundException;
import com.shoplify.ecommerce_springboot.model.Category;
import com.shoplify.ecommerce_springboot.model.Product;
import com.shoplify.ecommerce_springboot.repository.CategoryRepository;
import com.shoplify.ecommerce_springboot.repository.ProductImageRepository;
import com.shoplify.ecommerce_springboot.repository.ProductRepository;
import com.shoplify.ecommerce_springboot.service.FileService;
import com.shoplify.ecommerce_springboot.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository product_db;
    ProductImageRepository productimage_db;
    CategoryRepository category_db;
    FileService fileService;

    public ProductServiceImpl(ProductRepository product_db, CategoryRepository category_db, ProductImageRepository productimage_db, FileService fileService) {
        this.product_db = product_db;
        this.category_db = category_db;
        this.productimage_db = productimage_db;
        this.fileService = fileService;
    }

    public List<Product> findAllProducts() {
        return product_db.findAll();
    }

    @Transactional
    public Product saveProduct(ProductForm dto) {
        Category referenceCategory = category_db.findById(dto.category()).orElseThrow(() -> new ResourceNotFoundException("Category ID "+dto.category()+" was not found"));

        try {
            String test = fileService.saveFile(dto.file());
        } catch (Exception e) {
            System.out.println("EXCEPTION");
        }

        Product createdProduct = new Product();

        createdProduct.setName(dto.name());
        createdProduct.setType(dto.type());
        createdProduct.setDescription(dto.description());
        createdProduct.setPrice(dto.price());
        createdProduct.setStock(dto.stock());
        createdProduct.setCategory(referenceCategory);

        return product_db.save(createdProduct);
    }

    public Product getProduct(long id) {
        return product_db.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with ID "+ id + " not found"));
    }

    @Transactional
    public Product updateProduct(long id, ProductForm dto) {
        Product productToUpdate = this.getProduct(id);
        productToUpdate.setName(dto.name());
        productToUpdate.setType(dto.type());
        productToUpdate.setDescription(dto.description());
        productToUpdate.setPrice(dto.price());
        productToUpdate.setStock(dto.stock());

        return product_db.save(productToUpdate);
    }

    @Transactional
    public Product deleteProduct(long id) {
        Product productToUpdate = this.getProduct(id);

        product_db.delete(productToUpdate);

        return productToUpdate;
    }
}
