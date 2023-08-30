package zw.co.kez.productservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.productservice.enums.ProductStatus;
import zw.co.kez.productservice.models.Category;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Id
    private Long productId;
    @NotBlank(message = "The name cannot be empty")
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String dateCreated;
    private String lastUpdated;
    private String image;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private CategoryDTO category;
}
