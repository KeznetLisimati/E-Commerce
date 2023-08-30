package zw.co.kez.cartservice.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.cartservice.enums.CartStatus;
import zw.co.kez.cartservice.enums.PaymentMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequestDTO {

    private Long productId;
    private double totalAmount;
    private int quantity;
    private CartStatus cartStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
}
