package com.shoplify.ecommerce_springboot.controller;

import DTO.ProductForm;
import DTO.APIResponse;
import com.shoplify.ecommerce_springboot.model.Product;
import com.shoplify.ecommerce_springboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http//localhost:5173")
public class ProductController {

    private final ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>>listProducts() {

        List<Product> allProducts = productService.findAllProducts();

        APIResponse<List<Product>> response = new APIResponse<>(
                HttpStatus.OK.value(),
                true,
                "Fetched Product Successfully",
                allProducts
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> fetchProduct(@PathVariable long id) {
        Product product = productService.getProduct(id);

        APIResponse<Product> response = new APIResponse<>(
                HttpStatus.OK.value(),
                true,
                "Fetched Product Successfully",
                product
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<APIResponse<Product>> createProduct(@Valid @ModelAttribute ProductForm dto) {
        Product newProduct = productService.saveProduct(dto);

        APIResponse<Product> response = new APIResponse<>(
                HttpStatus.CREATED.value(),
                true,
                "Product created Successfully",
                newProduct
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> updateProduct(@PathVariable Long id, @Valid @ModelAttribute ProductForm dto) {
        Product updatedProduct = productService.updateProduct(id, dto);

        APIResponse<Product> response = new APIResponse<>(
                HttpStatus.OK.value(),
                true,
                "Product ID "+id+" was updated Successfully",
                updatedProduct
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> deleteProduct(@PathVariable Long id) {
        Product deletedProduct = productService.deleteProduct(id);

        APIResponse<Product> response = new APIResponse<>(
                HttpStatus.OK.value(),
                true,
                "Product ID "+id+" was deleted Successfully",
                deletedProduct
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
