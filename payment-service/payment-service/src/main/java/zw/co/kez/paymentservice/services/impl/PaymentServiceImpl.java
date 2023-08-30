package zw.co.kez.paymentservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.kez.paymentservice.dtos.PaymentRequestDTO;
import zw.co.kez.paymentservice.dtos.PaymentResponseDTO;
import zw.co.kez.paymentservice.enums.PaymentStatus;
import zw.co.kez.paymentservice.exceptions.PaymentServiceException;
import zw.co.kez.paymentservice.models.TransactionDetails;
import zw.co.kez.paymentservice.repositories.TransactionRepository;
import zw.co.kez.paymentservice.services.PaymentService;
import zw.co.kez.paymentservice.utils.RandomNumberGenerator;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final TransactionRepository transactionRepository;
    @Override
    public Long doPayment(PaymentRequestDTO paymentRequestDTO) {
        log.info("PaymentServiceImpl | doPayment | Recording Payment Details: {}", paymentRequestDTO);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .cartId(paymentRequestDTO.getCartId())
                .amount(paymentRequestDTO.getAmount())
                .referenceNumber(RandomNumberGenerator.generateCode())
                .paymentMode(paymentRequestDTO.getPaymentMode())
                .paymentStatus(PaymentStatus.SUCCESS)
                .build();

        transactionRepository.save(transactionDetails);
        log.info("Transaction Completed with Id: {}", transactionDetails.getTransactionId());

        return transactionDetails.getTransactionId();
    }

    @Override
    public PaymentResponseDTO getPaymentDetailsByCartId(Long cartId) {

        log.info("PaymentServiceImpl | getPaymentDetailsByCartId | Getting payment details for the Cart Id: {}", cartId);

        TransactionDetails transactionDetails
                = transactionRepository.findByCartId(cartId)
                .orElseThrow(() -> new PaymentServiceException(
                        "Transaction details with given id not found",
                        "TRANSACTION_NOT_FOUND", 404));

        PaymentResponseDTO paymentResponse
                = PaymentResponseDTO.builder()
                .paymentId(transactionDetails.getTransactionId())
                .paymentMode(transactionDetails.getPaymentMode())
                .paymentDate(transactionDetails.getPaymentDate())
                .cartId(transactionDetails.getCartId())
                .paymentStatus(transactionDetails.getPaymentStatus())
                .referenceNumber(transactionDetails.getReferenceNumber())
                .amount(transactionDetails.getAmount())
                .build();

        log.info("PaymentServiceImpl | getPaymentDetailsByCartId | paymentResponse: {}", paymentResponse.toString());

        return paymentResponse;
    }
}
