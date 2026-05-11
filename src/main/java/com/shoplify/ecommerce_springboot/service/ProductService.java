package com.shoplify.ecommerce_springboot.service;

import com.shoplify.ecommerce_springboot.DTO.ProductForm;
import com.shoplify.ecommerce_springboot.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProducts();
    public Product saveProduct(ProductForm dto);
    public Product getProduct(long id);
    public Product updateProduct(long id, ProductForm dto);
    public Product deleteProduct(long id);
}