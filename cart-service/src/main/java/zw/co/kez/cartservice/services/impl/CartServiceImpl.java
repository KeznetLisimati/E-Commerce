package zw.co.kez.cartservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zw.co.kez.cartservice.dtos.*;
import zw.co.kez.cartservice.enums.CartStatus;
import zw.co.kez.cartservice.exceptions.CartServiceException;
import zw.co.kez.cartservice.models.CartDetails;
import zw.co.kez.cartservice.repositories.CartRepository;
import zw.co.kez.cartservice.services.CartService;
import zw.co.kez.cartservice.utils.RandomNumberGenerator;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;
    @Override
    public long placeOrder(CartRequestDTO cartRequestDTO) {
        log.info("CartServiceImpl | placeOrder | Placing Order Request cartRequestDTO : " + cartRequestDTO.toString());

        CartDetails cartDetails = CartDetails.builder()
                .productId(cartRequestDTO.getProductId())
                .amount(cartRequestDTO.getTotalAmount())
                .quantity(cartRequestDTO.getQuantity())
                .orderDate(Instant.now())
                .cartStatus(CartStatus.CREATED)
                .build();

        cartDetails = cartRepository.save(cartDetails);

        log.info("CartServiceImpl | placeOrder | Calling Payment Service to complete the payment");

        PaymentRequestDTO paymentRequestDTO = PaymentRequestDTO.builder()
                .cartId(cartDetails.getCartId())
                .amount(cartRequestDTO.getTotalAmount())
                .paymentMode(cartRequestDTO.getPaymentMode())
                .referenceNumber(RandomNumberGenerator.generateCode())
                .build();

        CartStatus cartStatus = null;

        try {
            log.info("CartServiceImpl | placeOrder | Payment done Successfully. Changing the Oder status to PLACED");
            cartStatus = CartStatus.PLACED;
        } catch (Exception e) {
            log.error("CartServiceImpl | placeOrder | Error occurred in payment. Changing order status to PAYMENT_FAILED");
            cartStatus = CartStatus.PAYMENT_FAILED;
            log.error(e.getMessage());
        }

        cartDetails.setCartStatus(cartStatus);

        cartRepository.save(cartDetails);

        log.info("OrderServiceImpl | placeOrder | Order Places successfully with Order Id: {}", cartDetails.getCartId());

        return cartDetails.getCartId();
    }

    @Override
    public CartResponseDTO getCartDetails(Long cartId) {
        log.info("CartServiceImpl | getCartDetails | Get order details for Cart Id : {}", cartId);

        CartDetails cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartServiceException("Order not found for the cart Id:" + cartId,
                        "CART_NOT_FOUND", 404));

        log.info("CartServiceImpl | getOrderDetails | Invoking Product service to fetch the product for id: {}", cart.getProductId());

        ProductResponseDTO productResponse
                = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/api/product/findById/" + cart.getProductId(),
                ProductResponseDTO.class
        );

        log.info("CartServiceImpl | getCartDetails | Getting payment information form the payment Service");
        PaymentResponseDTO paymentResponse
                = restTemplate.getForObject(
                "http://PAYMENT-SERVICE/api/payment/getPaymentDetailsByCartId/" + cart.getCartId(),
                PaymentResponseDTO.class
        );

        assert productResponse != null: "Product response has missing field!";

        CartResponseDTO.ProductDetails productDetails
                = CartResponseDTO.ProductDetails
                .builder()
                .productName(productResponse.getName())
                .productId(productResponse.getProductId())
                .quantity(productResponse.getQuantity())
                .price(productResponse.getPrice())
                .build();

        assert paymentResponse != null: "Payment response contains null value!";

        CartResponseDTO.PaymentDetails paymentDetails
                = CartResponseDTO.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getPaymentStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        CartResponseDTO cartResponse
                = CartResponseDTO.builder()
                .cartId(cart.getCartId())
                .cartStatus(cart.getCartStatus())
                .amount(cart.getAmount())
                .orderDate(cart.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        log.info("CartServiceImpl | getCartDetails | cartResponse : " + cartResponse.toString());

        return cartResponse;
    }
}
