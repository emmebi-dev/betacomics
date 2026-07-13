package com.betacomics.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.output.CartDTO;
import com.betacomics.dto.output.CartItemDTO;
import com.betacomics.dto.output.ProductDTO;
import com.betacomics.exceptions.InsufficientStockException;
import com.betacomics.exceptions.ResourceNotFoundException;
import com.betacomics.models.Cart;
import com.betacomics.models.CartItem;
import com.betacomics.models.Product;
import com.betacomics.models.User;
import com.betacomics.repositories.CartRepository;
import com.betacomics.repositories.ProductRepository;
import com.betacomics.repositories.UserRepository;
import com.betacomics.services.interfaces.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = getOrCreateCartEntity(userId);
        return mapToCartDTO(cart);
    }

    @Override
    public CartDTO addItemToCart(Long userId, Long productId, Integer quantity) {
        Cart cart = getOrCreateCartEntity(userId);
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto non trovato con ID: " + productId));

        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException("Stock insufficiente per " + product.getName() + ". Disponibili: " + product.getStockQuantity());
        }

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + quantity;
            
            if (product.getStockQuantity() < newQuantity) {
                throw new InsufficientStockException("Non puoi aggiungere altri pezzi. Stock massimo raggiunto per " + product.getName());
            }
            item.setQuantity(newQuantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .product(product)
                    .quantity(quantity)
                    .build();
            cart.addItem(newItem);
        }

        Cart savedCart = cartRepository.save(cart);
        return mapToCartDTO(savedCart);
    }

    
    @Override
    public CartDTO updateItemQuantity(Long userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrello non trovato per l'utente: " + userId));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto non presente nel carrello"));

     
        if (item.getProduct().getStockQuantity() < quantity) {
            throw new InsufficientStockException("Impossibile aggiornare. Stock disponibile: " + item.getProduct().getStockQuantity());
        }

        item.setQuantity(quantity);
        Cart savedCart = cartRepository.save(cart);
        return mapToCartDTO(savedCart);
    }

    @Override
    public CartDTO removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrello non trovato per l'utente: " + userId));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto non trovato nel carrello"));

        cart.removeItem(item);
        Cart savedCart = cartRepository.save(cart);
        return mapToCartDTO(savedCart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrello non trovato per l'utente: " + userId));
        
        cart.getItems().clear();
        cartRepository.save(cart);
    }

	private Cart getOrCreateCartEntity(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con ID: " + userId));
                    Cart newCart = Cart.builder()
                            .user(user)
                            .items(new ArrayList<>())
                            .build();
                    return cartRepository.save(newCart);
                });
    }
	
	
	//TO-DO move to CartMap
    private CartDTO mapToCartDTO(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems().stream().map(item -> {
            
            
            BigDecimal price = item.getProduct().getPrice();
            BigDecimal subTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));

            
            ProductDTO prodDTO = ProductDTO.builder()
                    .id(item.getProduct().getId())
                    .name(item.getProduct().getName())
                    .price(price)
                    .build();

            return CartItemDTO.builder()
                    .id(item.getId())
                    .product(prodDTO)
                    .quantity(item.getQuantity())
                    .subTotal(subTotal)
                    .build();
        }).collect(Collectors.toList());

        
        BigDecimal totalCartPrice = itemDTOs.stream()
                .map(CartItemDTO::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .items(itemDTOs)
                .totalCartPrice(totalCartPrice)
                .build();
    }
}