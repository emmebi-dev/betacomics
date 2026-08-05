package com.betacomics.services.interfaces;

import com.betacomics.dto.input.CheckoutRequest;
import com.betacomics.dto.input.UpdateOrderStatusRequest;
import com.betacomics.dto.output.OrderDTO;
import com.betacomics.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    
    OrderDTO checkout(Long userId, CheckoutRequest request);
    
    OrderDTO getOrderDetails(Long orderId);
    
    List<OrderDTO> getOrderHistory(Long userId);
    
    OrderDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);
    
    Page<OrderDTO> getAllOrders(Pageable pageable, Optional<OrderStatus> status);

    Page<OrderDTO> getUserOrders(String usernameOrEmail, Pageable pageable, Optional<OrderStatus> status);

}