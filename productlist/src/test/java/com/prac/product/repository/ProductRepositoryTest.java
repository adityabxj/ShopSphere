package com.prac.product.repository;

import com.prac.product.entity.Category;
import com.prac.product.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Product product;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategoryName("Electronics");
        categoryRepository.save(category);

        product = new Product();
        product.setProductName("Laptop");
        product.setDescription("A high-end gaming laptop");
        product.setPrice(1500.00);
        product.setCategory(category);
        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void testFindById() {
        Optional<Product> foundProduct = productRepository.findById(product.getProduct_id());
        assertTrue(foundProduct.isPresent());
        assertEquals(product.getProductName(), foundProduct.get().getProductName());
    }

    @Test
    void testFindAll() {
        List<Product> products = productRepository.findAll();
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
    }

    @Test
    void testSave() {
        Product newProduct = new Product();
        newProduct.setProductName("Smartphone");
        newProduct.setDescription("A latest model smartphone");
        newProduct.setPrice(800.00);
        newProduct.setCategory(category);
        Product savedProduct = productRepository.save(newProduct);
        assertNotNull(savedProduct);
        assertEquals(newProduct.getProductName(), savedProduct.getProductName());
    }

    @Test
    void testDelete() {
        productRepository.delete(product);
        Optional<Product> foundProduct = productRepository.findById(product.getProduct_id());
        assertFalse(foundProduct.isPresent());
    }
}