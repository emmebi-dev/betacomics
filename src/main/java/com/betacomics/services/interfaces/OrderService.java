package com.betacomics.services.interfaces;

import com.betacomics.dto.input.CheckoutRequest;
import com.betacomics.dto.input.UpdateOrderStatusRequest;
import com.betacomics.dto.output.OrderDTO;
import java.util.List;

public interface OrderService {
    
    OrderDTO checkout(Long userId, CheckoutRequest request);
    
    OrderDTO getOrderDetails(Long userId, Long orderId);
    
    List<OrderDTO> getOrderHistory(Long userId);
    
    OrderDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);
}