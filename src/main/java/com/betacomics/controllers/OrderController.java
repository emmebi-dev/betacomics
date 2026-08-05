package com.betacomics.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.CheckoutRequest;
import com.betacomics.dto.input.UpdateOrderStatusRequest;
import com.betacomics.dto.output.OrderDTO;
import com.betacomics.enums.OrderStatus;
import com.betacomics.security.CurrentUserProvider;
import com.betacomics.services.interfaces.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CurrentUserProvider currentUserProvider;

    @PostMapping("/checkout")
    public ResponseEntity<OrderDTO> checkout(@Valid @RequestBody CheckoutRequest request) {
        Long userId = currentUserProvider.getCurrentUserId();
        OrderDTO order = orderService.checkout(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderDTO>> getOrderHistory() {
        Long userId = currentUserProvider.getCurrentUserId();
        List<OrderDTO> history = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long orderId) {
        Long userId = currentUserProvider.getCurrentUserId();
        OrderDTO order = orderService.getOrderDetails(userId, orderId);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        
        OrderDTO updatedOrder = orderService.updateOrderStatus(orderId, request);
        return ResponseEntity.ok(updatedOrder);
    }
    
    @GetMapping("/list")
    public ResponseEntity<Page<OrderDTO>> list(Pageable pageable, @RequestParam(required = false) OrderStatus status) {
        return ResponseEntity.ok(orderService.getAllOrders(pageable, Optional.ofNullable(status)));
    }
    
    @GetMapping("/listByUser")
    public ResponseEntity<Page<OrderDTO>> getUserOrders(
    		@RequestParam String query,
    		Pageable pageable,
    		@RequestParam(required = false) OrderStatus status) {
    	return ResponseEntity.ok(orderService.getUserOrders(query, pageable, Optional.ofNullable(status)));
    }
}