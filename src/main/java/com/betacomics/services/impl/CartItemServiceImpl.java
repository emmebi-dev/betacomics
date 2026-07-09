package com.betacomics.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.input.CartItemReq;
import com.betacomics.models.Cart;
import com.betacomics.models.CartItem;
import com.betacomics.models.Product;
import com.betacomics.repositories.CartItemRepository;
import com.betacomics.repositories.CartRepository;
import com.betacomics.repositories.ProductRepository;
import com.betacomics.services.interfaces.CartItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository; 

    @Transactional
    @Override
    public void addItem(CartItemReq req) {
        log.debug("Adding product {} to cart {}", req.getProductId(), req.getCartId());

        Cart cart = cartRepository.findById(req.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found at id: " + req.getCartId()));

        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found at id: " + req.getProductId()));

        if (product.getStockQuantity() < req.getQuantity()) {
            throw new RuntimeException("Not enough stock for product: " + product.getName());
        }

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + req.getQuantity());
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(req.getQuantity())
                    .build();
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void updateQuantity(CartItemReq req) {
        log.debug("Updating quantity for cart item {}", req.getId());

        CartItem item = cartItemRepository.findById(req.getId())
                .orElseThrow(() -> new RuntimeException("CartItem not found at id: " + req.getId()));

        if (req.getQuantity() != null) {
            if (item.getProduct().getStockQuantity() < req.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + item.getProduct().getName());
            }
            item.setQuantity(req.getQuantity());
        }

        cartItemRepository.save(item);
    }

    @Transactional
    @Override
    public void removeItem(Long cartId, Long itemId) {
        log.debug("Removing cart item {} from cart {}", itemId, cartId);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found at id: " + cartId));

        boolean removed = cart.getItems().removeIf(item -> item.getId().equals(itemId));
        
        if (!removed) {
            throw new RuntimeException("Item not found in this cart");
        }

        cartRepository.save(cart);
    }
}