package com.prac.product.controller;

import com.prac.product.dto.ProductDTO;
import com.prac.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product REST API CRUD operations",
        description = "CREATE, READ, UPDATE, DELETE operations for Product REST API"
)
@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    //createProduct
    @Operation(
            summary = "Product Category",
            description = "REST API to create product"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    // getAllProducts
    @Operation(
            summary = "Fetch All Products",
            description = "REST API to fetch all Products"
    )
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // getProductById
    @Operation(
            summary = "Fetch Product By Id",
            description = "REST API to Fetch Product By Id"
    )
    @GetMapping("/get")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("productId") Long product_id){
        return new ResponseEntity<>(productService.getProductById(product_id), HttpStatus.OK);
    }

    // updateProduct
    @Operation(
            summary = "Update Product",
            description = "REST API to update Product"
    )
    @PutMapping("/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestParam("productId") Long product_id, @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.updateProduct(product_id, productDTO), HttpStatus.OK);
    }

    //deleteProduct
    @Operation(
            summary = "Delete Product",
            description = "REST API to delete Product"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("productId") Long product_id){
        return new ResponseEntity<>(productService.deleteProduct(product_id), HttpStatus.OK);
    }
}
