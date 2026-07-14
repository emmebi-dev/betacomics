package com.betacomics.services.interfaces;

import com.betacomics.dto.output.CartDTO;

public interface CartService {
	
	CartDTO getCartByUserId(Long userId);
    
	CartDTO addItemToCart(Long userId, Long productId, Integer quantity);
    CartDTO updateItemQuantity(Long userId, Long productId, Integer quantity);
    CartDTO removeItemFromCart(Long userId, Long productId);
    
    void clearCart(Long userId);
}