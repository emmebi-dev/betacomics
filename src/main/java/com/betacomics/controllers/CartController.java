package com.betacomics.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacomics.dto.input.AddToCartRequest;
import com.betacomics.dto.input.UpdateQuantityRequest;
import com.betacomics.dto.output.CartDTO;
import com.betacomics.security.CurrentUserProvider;
import com.betacomics.services.interfaces.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CurrentUserProvider currentUserProvider;

    @GetMapping
    public ResponseEntity<CartDTO> getCart() {
        Long userId = currentUserProvider.getCurrentUserId();
        CartDTO cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/items")
    public ResponseEntity<CartDTO> addItemToCart(@Valid @RequestBody AddToCartRequest request) {
        Long userId = currentUserProvider.getCurrentUserId();
        CartDTO updatedCart = cartService.addItemToCart(userId, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/items")
    public ResponseEntity<CartDTO> updateItemQuantity(@Valid @RequestBody UpdateQuantityRequest request) {
        Long userId = currentUserProvider.getCurrentUserId();
        CartDTO updatedCart = cartService.updateItemQuantity(userId, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable Long productId) {
        Long userId = currentUserProvider.getCurrentUserId();
        CartDTO updatedCart = cartService.removeItemFromCart(userId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    
    @DeleteMapping
    public ResponseEntity<Void> clearCart() {
        Long userId = currentUserProvider.getCurrentUserId();
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }    
}