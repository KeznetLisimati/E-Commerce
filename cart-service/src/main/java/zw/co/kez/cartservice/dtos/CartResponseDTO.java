package zw.co.kez.cartservice.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.cartservice.enums.CartStatus;
import zw.co.kez.cartservice.enums.PaymentMode;
import zw.co.kez.cartservice.enums.PaymentStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDTO {
    private Long cartId;
    private Instant orderDate;
    private CartStatus cartStatus;
    private double amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private String productName;
        private Long productId;
        private int quantity;
        private double price;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentDetails{
        private long paymentId;

        @Enumerated(EnumType.STRING)
        private PaymentMode paymentMode;
        private PaymentStatus paymentStatus;
        private Instant paymentDate;
    }
}
