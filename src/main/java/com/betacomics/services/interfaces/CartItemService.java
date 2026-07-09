package com.betacomics.services.interfaces;

import com.betacomics.dto.input.CartItemReq;

public interface CartItemService {
    void addItem(CartItemReq req);
    void updateQuantity(CartItemReq req);
    void removeItem(Long cartId, Long itemId);
}