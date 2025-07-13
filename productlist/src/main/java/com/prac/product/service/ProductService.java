package com.prac.product.service;

import com.prac.product.dto.ProductDTO;
import com.prac.product.entity.Category;
import com.prac.product.entity.Product;
import com.prac.product.mapper.CategoryMapper;
import com.prac.product.mapper.ProductMapper;
import com.prac.product.repository.CategoryRepository;
import com.prac.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO){
        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(()-> new RuntimeException("Category Not Found!"));
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }
}
