package com.prac.product.service;

import com.prac.product.dto.CategoryDTO;
import com.prac.product.entity.Category;
import com.prac.product.exception.CategoryAlreadyExistsException;
import com.prac.product.exception.CategoryNotFoundException;
import com.prac.product.mapper.CategoryMapper;
import com.prac.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if (optionalCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Category " + categoryDTO.getCategoryName() + " already exists!");
        }
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(category);
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDto).toList();
    }

    public CategoryDTO getCategoryById(Long category_id) {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new CategoryNotFoundException("Category id: "+ category_id +" Not Found!"));
        return CategoryMapper.toCategoryDto(category);
    }

    public String deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) throw new CategoryNotFoundException("Category ID: " + categoryId + " not found!");
        categoryRepository.deleteById(categoryId);
        return "Category deleted successfully";
    }
}