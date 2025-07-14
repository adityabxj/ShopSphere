package com.prac.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(
        name = "Category",
        description = "It holds category information along with their products"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long category_id;
    private String categoryName;
    private List<ProductDTO> products;
}
