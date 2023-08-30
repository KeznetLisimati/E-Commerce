package zw.co.kez.paymentservice.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.paymentservice.enums.PaymentMode;
import zw.co.kez.paymentservice.enums.PaymentStatus;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
    private Long paymentId;
    private double amount;
    private Instant paymentDate;
    private Long cartId;
    private String referenceNumber;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
}
