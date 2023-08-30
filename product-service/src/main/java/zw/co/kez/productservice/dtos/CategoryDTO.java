package zw.co.kez.productservice.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.productservice.models.Product;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    @Id
    private Long categoryId;
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @Column(length = 255,name = "description", nullable = true)
    private String description;
    private List<Product> products;

}