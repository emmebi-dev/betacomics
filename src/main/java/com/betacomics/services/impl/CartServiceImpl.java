package com.betacomics.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.output.CartDTO;
import com.betacomics.maps.CartMap;
import com.betacomics.models.Cart;
import com.betacomics.repositories.CartRepository;
import com.betacomics.services.interfaces.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Transactional
    @Override
    public CartDTO create() {
        log.debug("Creating a new permanent cart");
        Cart cart = new Cart();
        return CartMap.buildCartDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO getById(Long id) {
        log.debug("Fetching cart with id: {}", id);
        return cartRepository.findById(id)
                .map(CartMap::buildCartDTO)
                .orElseThrow(() -> new RuntimeException("Cart not found at id: " + id));
    }

    @Transactional
    @Override
    public CartDTO clear(Long id) {
        log.debug("Clearing content of cart with id: {}", id);
        
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found at id: " + id));
        
        cart.getItems().clear();
        
        return CartMap.buildCartDTO(cartRepository.save(cart));
    }
}