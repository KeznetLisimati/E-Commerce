package zw.co.kez.paymentservice.services;

import zw.co.kez.paymentservice.dtos.PaymentRequestDTO;
import zw.co.kez.paymentservice.dtos.PaymentResponseDTO;

public interface PaymentService {
    Long doPayment(PaymentRequestDTO paymentRequest);

    PaymentResponseDTO getPaymentDetailsByCartId(Long cartId);
}
