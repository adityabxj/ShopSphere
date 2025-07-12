package com.prac.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long product_id;
    private String product_name;
    private String description;
    private Double price;
    private Long category_id;
}
