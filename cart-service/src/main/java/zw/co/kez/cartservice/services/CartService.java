package zw.co.kez.cartservice.services;

import zw.co.kez.cartservice.dtos.CartRequestDTO;
import zw.co.kez.cartservice.dtos.CartResponseDTO;

public interface CartService {
    long placeOrder(CartRequestDTO cartRequestDTO);

    CartResponseDTO getCartDetails(Long cartId);
}
