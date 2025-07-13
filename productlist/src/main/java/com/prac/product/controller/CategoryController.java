package com.prac.product.controller;

import com.prac.product.dto.CategoryDTO;
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
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    // getAllCategories
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    // getCategoryById
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long category_id){
        return new ResponseEntity<>(categoryService.getCategoryById(category_id), HttpStatus.OK);
    }

    // deleteCategory
    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long category_id){
        return new ResponseEntity<>(categoryService.deleteCategory(category_id), HttpStatus.OK);
    }
}
