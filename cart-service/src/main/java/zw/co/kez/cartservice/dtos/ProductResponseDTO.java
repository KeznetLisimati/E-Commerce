package zw.co.kez.cartservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.cartservice.enums.ProductStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String dateCreated;
    private String lastUpdated;
    private String image;
    private ProductStatus productStatus;
    private Category category;
}
