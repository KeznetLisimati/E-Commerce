package zw.co.kez.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.productservice.enums.ProductStatus;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    private Long productId;
    private String name;
    @Column(length = 255,name = "description", nullable = true)
    private String description;
    private double price;
    private int quantity;
    private String dateCreated;
    private String lastUpdated;
    private String image;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @JsonIgnoreProperties({"product","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

}
