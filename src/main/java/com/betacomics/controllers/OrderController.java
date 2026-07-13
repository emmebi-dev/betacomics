package com.betacomics.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.CheckoutRequest;
import com.betacomics.dto.input.UpdateOrderStatusRequest;
import com.betacomics.dto.output.OrderDTO;
import com.betacomics.services.interfaces.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<OrderDTO> checkout(@Valid @RequestBody CheckoutRequest request) {
        Long userId = getCurrentUserId();
        OrderDTO order = orderService.checkout(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/history")
    public ResponseEntity<List<OrderDTO>> getOrderHistory() {
        Long userId = getCurrentUserId();
        List<OrderDTO> history = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long orderId) {
        Long userId = getCurrentUserId();
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


    private Long getCurrentUserId() {
        return 1L;
    }
}