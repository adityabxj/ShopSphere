package com.prac.product.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long category_id;
    private String category_name;
    private List<ProductDTO> products;
}
