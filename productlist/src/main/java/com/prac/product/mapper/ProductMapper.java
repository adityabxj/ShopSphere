package com.prac.product.mapper;

import com.prac.product.dto.ProductDTO;
import com.prac.product.entity.Category;
import com.prac.product.entity.Product;

public class ProductMapper {
    // entity to dto, dto to entity
    public static ProductDTO toProductDTO(Product product){
        return new ProductDTO(
                product.getProduct_id(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getCategory_id()
        );
    }

    public static Product toProductEntity(ProductDTO productDTO, Category category){
        Product product = new Product();
        product.setProduct_id(productDTO.getProduct_id());
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return  product;
    }
}
