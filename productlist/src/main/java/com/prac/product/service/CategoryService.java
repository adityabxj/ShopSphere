package com.prac.product.service;

import com.prac.product.dto.CategoryDTO;
import com.prac.product.entity.Category;
import com.prac.product.mapper.CategoryMapper;
import com.prac.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    //    getAllCategories, createCategory, getCategoryById, deleteCategory
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
      Category category = CategoryMapper.toCategoryEntity(categoryDTO);
      category = categoryRepository.save(category);
      return  CategoryMapper.toCategoryDto(category);
    }

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDto).toList();
    }

    public CategoryDTO getCategoryById(Long category_id){
       Category category = categoryRepository.findById(category_id).orElseThrow(()-> new RuntimeException("Category not found!"));
       return CategoryMapper.toCategoryDto(category);
    }

    public String deleteCategory(Long category_id){
        categoryRepository.findById(category_id).orElseThrow(()-> new RuntimeException("Category not found!"));
        return "Category deleted successfully";
    }
}
