package com.betacomics.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacomics.dto.input.CheckoutRequest;
import com.betacomics.dto.input.UpdateOrderStatusRequest;
import com.betacomics.dto.output.OrderDTO;
import com.betacomics.dto.output.OrderItemDTO;
import com.betacomics.dto.output.ProductDTO;
import com.betacomics.enums.OrderStatus;
import com.betacomics.exceptions.InsufficientStockException;
import com.betacomics.exceptions.ResourceNotFoundException;
import com.betacomics.models.Cart;
import com.betacomics.models.CartItem;
import com.betacomics.models.Order;
import com.betacomics.models.OrderItem;
import com.betacomics.models.Product;
import com.betacomics.repositories.CartRepository;
import com.betacomics.repositories.OrderRepository;
import com.betacomics.repositories.ProductRepository;
import com.betacomics.services.interfaces.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderDTO checkout(Long userId, CheckoutRequest request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrello non trovato per l'utente: " + userId));

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Impossibile effettuare il checkout: il carrello è vuoto.");
        }

        Order order = Order.builder()
                .user(cart.getUser())
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .totalPrice(BigDecimal.ZERO)
                .build();

        BigDecimal calculatedTotal = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            int requestedQty = cartItem.getQuantity();

            if (product.getStockQuantity() < requestedQty) {
                throw new InsufficientStockException("Resta solo " + product.getStockQuantity() + " pz di " + product.getName() + ". Riduci la quantità nel carrello.");
            }

            product.setStockQuantity(product.getStockQuantity() - requestedQty);
            productRepository.save(product);

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(requestedQty)
                    .priceAtPurchase(product.getPrice())
                    .build();

            order.addItem(orderItem);

            BigDecimal itemSubtotal = product.getPrice().multiply(BigDecimal.valueOf(requestedQty));
            calculatedTotal = calculatedTotal.add(itemSubtotal);
        }

        order.setTotalPrice(calculatedTotal);

        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return mapToOrderDTO(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDTO getOrderDetails(Long userId, Long orderId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato per questo utente. ID: " + orderId));
        return mapToOrderDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> getOrderHistory(Long userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId).stream()
                .map(this::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con ID: " + orderId));
        
        order.setStatus(request.getStatus());
        return mapToOrderDTO(orderRepository.save(order));
    }

    //TO-DO move to OrderMap
    private OrderDTO mapToOrderDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> {
            BigDecimal subTotal = item.getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getQuantity()));
            
            ProductDTO prodDTO = ProductDTO.builder()
                    .id(item.getProduct().getId())
                    .name(item.getProduct().getName())
                    .imageUrl(item.getProduct().getImageUrl())
                    .build();

            return OrderItemDTO.builder()
                    .id(item.getId())
                    .product(prodDTO)
                    .priceAtPurchase(item.getPriceAtPurchase())
                    .quantity(item.getQuantity())
                    .subTotal(subTotal)
                    .build();
        }).collect(Collectors.toList());

        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .orderDate(order.getOrderDate())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .items(itemDTOs)
                .build();
    }
}