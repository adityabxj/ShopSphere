package com.prac.product.controller;

import com.prac.product.dto.CategoryDTO;
import com.prac.product.exception.CategoryAlreadyExistsException;
import com.prac.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    public List<CategoryDTO> categories;

    // createCategory
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // getAllCategories
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    // getCategoryById
    @GetMapping("/get")
    public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam("categoryId") Long category_id){
        return new ResponseEntity<>(categoryService.getCategoryById(category_id), HttpStatus.OK);
    }

    // deleteCategory
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCategoryById(@RequestParam("categoryId") Long category_id){
        return new ResponseEntity<>(categoryService.deleteCategory(category_id), HttpStatus.OK);
    }
}
