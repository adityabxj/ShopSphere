package com.prac.product.controller;

import com.prac.product.dto.ProductDTO;
import com.prac.product.service.ProductService;
import lombok.With;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO();
        productDTO.setProduct_id(1L);
        productDTO.setProductName("Laptop");
        productDTO.setDescription("A high-end gaming laptop");
        productDTO.setPrice(1500.00);
        productDTO.setCategory_id(1L);
    }

    @Test
    void testGetAllProducts() {
        when(productService.getAllProducts()).thenReturn(List.of(productDTO));

        List<ProductDTO> products = productController.getAllProducts().getBody();

        assertNotNull(products);
        assertEquals(1, products.size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        when(productService.getProductById(any(Long.class))).thenReturn(productDTO);

        ProductDTO foundProduct = productController.getProductById(1L).getBody();

        assertNotNull(foundProduct);
        assertEquals(productDTO.getProductName(), foundProduct.getProductName());
        verify(productService, times(1)).getProductById(any(Long.class));
    }

    @Test
    @WithMockUser(authorities = "ROLE_SELLER")
    void testCreateProduct() {
        when(productService.createProduct(any(ProductDTO.class))).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productDTO.getProductName(), response.getBody().getProductName());
        verify(productService, times(1)).createProduct(any(ProductDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ROLE_SELLER")
    void testUpdateProduct() {
        when(productService.updateProduct(any(Long.class), any(ProductDTO.class))).thenReturn(productDTO);

        ProductDTO updatedProduct = productController.updateProduct(1L, productDTO).getBody();

        assertNotNull(updatedProduct);
        assertEquals(productDTO.getProductName(), updatedProduct.getProductName());
        verify(productService, times(1)).updateProduct(any(Long.class), any(ProductDTO.class));
    }

    @Test
    @WithMockUser(authorities = "ROLE_SELLER")
    void testDeleteProduct() {
        when(productService.deleteProduct(any(Long.class))).thenReturn("Product 1 has been deleted!");

        String result = String.valueOf(productController.deleteProduct(1L));

        assertEquals("Product 1 has been deleted!", result);
        verify(productService, times(1)).deleteProduct(any(Long.class));
    }
}