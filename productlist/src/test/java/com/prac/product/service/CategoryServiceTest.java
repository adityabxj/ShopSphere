package com.prac.product.service;

import com.prac.product.dto.CategoryDTO;
import com.prac.product.entity.Category;
import com.prac.product.exception.CategoryAlreadyExistsException;
import com.prac.product.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategory_id(1L);
        category.setCategoryName("testCategory");
        categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(1L);
        categoryDTO.setCategoryName("testCategory");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCategory() {
        when(categoryRepository.findByCategoryName(categoryDTO.getCategoryName())).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        assertNotNull(savedCategory);
        assertEquals(categoryDTO.getCategoryName(), savedCategory.getCategoryName());
    }

    @Test
    void createCategory_ShouldThrowException_WhenCategoryAlreadyExists() {
        when(categoryRepository.findByCategoryName(categoryDTO.getCategoryName())).thenReturn(Optional.of(category));
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.createCategory(categoryDTO));
        verify(categoryRepository, times(0)).save(any(Category.class));
    }
}