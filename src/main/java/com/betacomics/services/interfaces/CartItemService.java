package com.betacomics.services.interfaces;

import com.betacomics.dto.input.CartItemReq;
import com.betacomics.dto.output.CartDTO;

public interface CartItemService {
    CartDTO addItem(CartItemReq req);
    CartDTO updateQuantity(CartItemReq req);
    CartDTO removeItem(Long cartId, Long itemId);
}