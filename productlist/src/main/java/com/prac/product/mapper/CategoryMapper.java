package com.prac.product.mapper;

import com.prac.product.dto.CategoryDTO;
import com.prac.product.entity.Category;

public class CategoryMapper {
// entity to dto, dto to entity
    public static  CategoryDTO toCategoryDto(Category category){
        if(category == null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(category.getCategory_id());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setProducts(category.getProducts().stream()
                .map(ProductMapper::toProductDTO).toList());
        return categoryDTO;
    }

    public  static Category toCategoryEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }
}
