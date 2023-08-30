package zw.co.kez.cartservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.cartservice.enums.CartStatus;

import java.time.Instant;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    private Long productId;
    private Instant orderDate;
    private int quantity;
    private double amount;
    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

}
