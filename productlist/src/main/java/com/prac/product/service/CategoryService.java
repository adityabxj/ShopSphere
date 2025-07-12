package com.prac.product.service;

import com.prac.product.dto.CategoryDTO;
import com.prac.product.entity.Category;
import com.prac.product.mapper.CategoryMapper;
import com.prac.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
