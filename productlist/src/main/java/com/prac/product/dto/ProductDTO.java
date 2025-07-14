package com.prac.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Product",
        description = "It holds product's information"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long product_id;
    private String productName;
    private String description;
    private Double price;
    private Long category_id;
}
