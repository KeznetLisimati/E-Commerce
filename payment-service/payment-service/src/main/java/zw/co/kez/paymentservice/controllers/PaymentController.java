package zw.co.kez.paymentservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.kez.paymentservice.dtos.PaymentRequestDTO;
import zw.co.kez.paymentservice.dtos.PaymentResponseDTO;
import zw.co.kez.paymentservice.services.PaymentService;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<String> doPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {

        log.info("PaymentController | doPayment | paymentRequest : " + paymentRequestDTO.toString());
        paymentService.doPayment(paymentRequestDTO);
        return new ResponseEntity<>("Order with cart Id '"+paymentRequestDTO.getCartId()+"' " +
                "is successfully paid", HttpStatus.OK);
    }

    @GetMapping("/getPaymentDetailsByCartId/{cartId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentDetailsByOrderId(@PathVariable long cartId) {

        log.info("PaymentController | doPayment | cartId : " + cartId);

        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByCartId(cartId),
                HttpStatus.OK
        );
    }
}
