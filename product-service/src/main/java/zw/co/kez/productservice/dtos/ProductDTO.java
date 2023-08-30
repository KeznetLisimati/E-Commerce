package zw.co.kez.productservice.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.productservice.enums.ProductStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "The name cannot be empty")
    private String name;
    private String description;
    private ProductStatus productStatus;
    private int quantity;@Enumerated(EnumType.STRING)
    private CategoryDTO category;
}
