package com.prac.product.controller;

import com.prac.product.dto.ProductDTO;
import com.prac.product.entity.Product;
import com.prac.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
//    getProduct, createProduct, updateProduct, getProductById, deleteProduct

    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }
}
