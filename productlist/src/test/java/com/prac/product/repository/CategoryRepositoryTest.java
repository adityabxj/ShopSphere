package com.prac.product.repository;

import com.prac.product.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryName("testCategory");
        categoryRepository.save(category);
    }

    @AfterEach
    void tearDown() {
        categoryRepository.delete(category);
    }

    @Test
    void findByCategoryName() {
        Category categoryFound = categoryRepository.findByCategoryName("testCategory").orElse(null);
        assertNotNull(categoryFound);
        assertEquals(category.getCategoryName(), categoryFound.getCategoryName());
    }
}