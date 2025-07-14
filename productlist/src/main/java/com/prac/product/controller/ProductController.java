package com.prac.product.controller;

import com.prac.product.dto.ProductDTO;
import com.prac.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    //createProduct
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    // getAllProducts
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // getProductById
    @GetMapping("/get")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("productId") Long product_id){
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
    }

    // updateProduct
    @PutMapping("/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestParam("productId") Long product_id, @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.updateProduct(product_id, productDTO), HttpStatus.OK);
    }

    //deleteProduct
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("productId") Long product_id){
        return new ResponseEntity<>(productService.deleteProduct(product_id), HttpStatus.OK);
    }
}
