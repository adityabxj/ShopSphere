package com.prac.product.service;

import com.prac.product.dto.ProductDTO;
import com.prac.product.entity.Category;
import com.prac.product.entity.Product;
import com.prac.product.exception.CategoryNotFoundException;
import com.prac.product.repository.CategoryRepository;
import com.prac.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Category category;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setCategory_id(1L);
        category.setCategoryName("Electronics");

        product = new Product();
        product.setProduct_id(1L);
        product.setProductName("Laptop");
        product.setDescription("A high-end gaming laptop");
        product.setPrice(1500.00);
        product.setCategory(category);

        productDTO = new ProductDTO();
        productDTO.setProduct_id(1L);
        productDTO.setProductName("Laptop");
        productDTO.setDescription("A high-end gaming laptop");
        productDTO.setPrice(1500.00);
        productDTO.setCategory_id(1L);
    }

    @AfterEach
    void tearDown() {
        reset(productRepository);
        reset(categoryRepository);
    }
    @Test
    void testCreateProduct() {
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO createdProduct = productService.createProduct(productDTO);

        assertNotNull(createdProduct);
        assertEquals(productDTO.getProductName(), createdProduct.getProductName());
        verify(categoryRepository, times(1)).findById(any(Long.class));
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProductCategoryNotFound() {
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> productService.createProduct(productDTO));
        verify(categoryRepository, times(1)).findById(any(Long.class));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductDTO> products = productService.getAllProducts();

        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));

        ProductDTO foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(product.getProductName(), foundProduct.getProductName());
        verify(productRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(product));
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO updatedProduct = productService.updateProduct(1L, productDTO);

        assertNotNull(updatedProduct);
        assertEquals(productDTO.getProductName(), updatedProduct.getProductName());
        verify(productRepository, times(1)).findById(any(Long.class));
        verify(categoryRepository, times(1)).findById(any(Long.class));
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(any(Long.class));

        String result = productService.deleteProduct(1L);

        assertEquals("Product 1 has been deleted!", result);
        verify(productRepository, times(1)).deleteById(any(Long.class));
    }
}