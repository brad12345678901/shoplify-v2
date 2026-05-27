package com.shoplify.ecommerce_springboot.service;

import com.shoplify.ecommerce_springboot.DTO.ProductForm;
import com.shoplify.ecommerce_springboot.DTO.ProductResourceList;
import com.shoplify.ecommerce_springboot.entity.Product;

import java.util.List;

public interface ProductService {
    public List<ProductResourceList> findAllProducts();
    public Product saveProduct(ProductForm dto);
    public Product getProduct(long id);
    public Product updateProduct(long id, ProductForm dto);
    public Product deleteProduct(long id);
}