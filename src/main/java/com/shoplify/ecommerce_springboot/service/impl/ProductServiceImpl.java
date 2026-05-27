package com.shoplify.ecommerce_springboot.service.impl;

import com.shoplify.ecommerce_springboot.DTO.ProductForm;
import com.shoplify.ecommerce_springboot.DTO.ProductResourceList;
import com.shoplify.ecommerce_springboot.exception.ResourceNotFoundException;
import com.shoplify.ecommerce_springboot.entity.Category;
import com.shoplify.ecommerce_springboot.entity.Product;
import com.shoplify.ecommerce_springboot.entity.ProductImage;
import com.shoplify.ecommerce_springboot.repository.CategoryRepository;
import com.shoplify.ecommerce_springboot.repository.ProductImageRepository;
import com.shoplify.ecommerce_springboot.repository.ProductRepository;
import com.shoplify.ecommerce_springboot.service.FileService;
import com.shoplify.ecommerce_springboot.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository product_db;
    ProductImageRepository productImage_db;
    CategoryRepository category_db;
    FileService<ProductImage, Product> fileService;

    public ProductServiceImpl(ProductRepository product_db, CategoryRepository category_db, ProductImageRepository productImage_db, FileService<ProductImage, Product> fileService) {
        this.product_db = product_db;
        this.category_db = category_db;
        this.productImage_db = productImage_db;
        this.fileService = fileService;
    }

    public List<ProductResourceList> findAllProducts() {

        List<Product> products = product_db.findAll();

        return products.stream().map(product ->
            new ProductResourceList(
                    product.getId(),
                    product.getName(),
                    product.getType(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    product.getCategory().getId(),
                    product.getCategory().getName(),
                    product.getProductImages().stream().map(ProductImage::getFilepath).toList())
        ).toList();
    }

    @Transactional
    public Product saveProduct(ProductForm dto) {
        System.out.println(dto);
        Category referenceCategory = category_db.findById(dto.category()).orElseThrow(() -> new ResourceNotFoundException("Category ID "+dto.category()+" was not found"));
        ProductImage productImage;

        Product createdProduct = new Product();

        createdProduct.setName(dto.name());
        createdProduct.setType(dto.type());
        createdProduct.setDescription(dto.description());
        createdProduct.setPrice(dto.price());
        createdProduct.setStock(dto.stock());
        createdProduct.setCategory(referenceCategory);

        Product product = product_db.save(createdProduct);

        if (!dto.file().isEmpty()) {
            try {
                productImage = fileService.saveFile(product, dto.file());
                productImage_db.save(productImage);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return product;
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
