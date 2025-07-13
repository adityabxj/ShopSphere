package com.prac.product.service;

import com.prac.product.dto.ProductDTO;
import com.prac.product.entity.Category;
import com.prac.product.entity.Product;
import com.prac.product.mapper.ProductMapper;
import com.prac.product.repository.CategoryRepository;
import com.prac.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProductDTO> getAllProducts(){
        return  productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    public ProductDTO getProductById(Long product_id){
       Product product = productRepository.findById(product_id).orElseThrow(()->new RuntimeException("Product not found!"));
       return ProductMapper.toProductDTO(product);
    }

    public ProductDTO updateProduct(Long product_id, ProductDTO productDTO){
        Product product = productRepository.findById(product_id).orElseThrow(()-> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(()-> new RuntimeException("Category not found"));
        product.setProduct_name(productDTO.getProduct_name());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public String deleteProduct(Long product_id){
        productRepository.deleteById(product_id);
        return "Product deleted successfully";
    }
}
