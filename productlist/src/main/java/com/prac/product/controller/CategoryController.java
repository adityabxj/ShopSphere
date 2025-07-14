package com.prac.product.controller;

import com.prac.product.dto.CategoryDTO;
import com.prac.product.exception.CategoryAlreadyExistsException;
import com.prac.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category REST API CRUD operations",
        description = "CREATE, READ, UPDATE, DELETE operations for Category REST API"
)
@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    public List<CategoryDTO> categories;

    // createCategory
    @Operation(
            summary = "Create Category",
            description = "REST API to create category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // getAllCategories
    @Operation(
            summary = "Fetch All Categories",
            description = "REST API to fetch all categories"
    )
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    // getCategoryById
    @Operation(
            summary = "Fetch Category By Id",
            description = "REST API to Fetch Category By Id"
    )
    @GetMapping("/get")
    public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam("categoryId") Long category_id){
        return new ResponseEntity<>(categoryService.getCategoryById(category_id), HttpStatus.OK);
    }

    // deleteCategory
    @Operation(
            summary = "Delete Category",
            description = "REST API to delete category"
    )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCategoryById(@RequestParam("categoryId") Long category_id){
        return new ResponseEntity<>(categoryService.deleteCategory(category_id), HttpStatus.OK);
    }
}
