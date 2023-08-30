package zw.co.kez.cartservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.kez.cartservice.dtos.CartRequestDTO;
import zw.co.kez.cartservice.dtos.CartResponseDTO;
import zw.co.kez.cartservice.services.CartService;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody CartRequestDTO cartRequestDTO) {

        log.info("CartController | placeOrder | cartRequest: {}", cartRequestDTO.toString());

            Long cartId = cartService.placeOrder(cartRequestDTO);
        log.info("Cart Id: {}", cartId);
        return new ResponseEntity<>("Order with Id '"+cartId+"' is successfully placed", HttpStatus.OK);
    }

    @GetMapping("/getCartDetails/{cartId}")
    public ResponseEntity<CartResponseDTO> getCartDetails(@PathVariable Long cartId) {

        CartResponseDTO cartResponse
                = cartService.getCartDetails(cartId);

        log.info("CartController | getCartDetails | cartResponse : " + cartResponse.toString());

        return new ResponseEntity<>(cartResponse,
                HttpStatus.OK);
    }
}
