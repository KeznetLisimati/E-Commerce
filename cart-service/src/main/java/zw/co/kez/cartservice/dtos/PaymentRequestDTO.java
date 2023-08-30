package zw.co.kez.cartservice.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.cartservice.enums.PaymentMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDTO {

    private Long cartId;
    private double amount;
    private String referenceNumber;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

}
