package zw.co.kez.cartservice.dtos;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.cartservice.enums.CartStatus;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    @Id
    private Long cartId;
    private Long productId;
    private Instant orderDate;
    private int quantity;
    private double amount;
    private CartStatus cartStatus;

}
