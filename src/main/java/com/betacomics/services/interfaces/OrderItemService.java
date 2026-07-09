package com.betacomics.services.interfaces;

import com.betacomics.dto.input.OrderItemReq;

public interface OrderItemService {
    void addItem(OrderItemReq req);
    void updateQuantity(OrderItemReq req);
}
