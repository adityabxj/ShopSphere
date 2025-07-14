package com.prac.product.service;

import com.prac.product.dto.ProductDTO;
import com.prac.product.entity.Category;
import com.prac.product.entity.Product;
import com.prac.product.exception.CategoryNotFoundException;
import com.prac.product.exception.ProductNotFoundException;
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
        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(()-> new CategoryNotFoundException("Category id: "+ productDTO.getCategory_id() +" Not Found!"));
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    public List<ProductDTO> getAllProducts(){
        return  productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    public ProductDTO getProductById(Long product_id){
       Product product = productRepository.findById(product_id).orElseThrow(()->new ProductNotFoundException("Product id: "+ product_id +" not found"));
       return ProductMapper.toProductDTO(product);
    }

    public ProductDTO updateProduct(Long product_id, ProductDTO productDTO){
        Product product = productRepository.findById(product_id).orElseThrow(()-> new ProductNotFoundException("Product id: "+ product_id +" not found"));
        Category category = categoryRepository.findById(productDTO.getCategory_id()).orElseThrow(()-> new CategoryNotFoundException("Category id: "+ productDTO.getCategory_id() +" Not Found!"));
        product.setProductName(productDTO.getProductName());
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
