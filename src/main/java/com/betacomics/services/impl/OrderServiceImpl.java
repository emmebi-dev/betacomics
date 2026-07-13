package com.betacomics.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacomics.dto.input.OrderItemReq;
import com.betacomics.dto.input.OrderReq;
import com.betacomics.dto.output.OrderDTO;
import com.betacomics.maps.OrderMap;
import com.betacomics.models.Order;
import com.betacomics.models.OrderItem;
import com.betacomics.models.Product;
import com.betacomics.models.User;
import com.betacomics.repositories.OrderRepository;
import com.betacomics.repositories.ProductRepository;
import com.betacomics.repositories.UserRepository;
import com.betacomics.services.interfaces.OrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
		
	@Transactional
	@Override
	public void create(OrderReq req) {
		log.debug("create Order {}", req);
        Order order = new Order();
        
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found at id: " + req.getUserId()));
        
        order.setOrderDate(req.getOrderDate());
        order.setStatus(req.getStatus());
        order.setUser(user);
        
        BigDecimal total = BigDecimal.ZERO;
        
        for (OrderItemReq itemReq : req.getOrderItems()) {
        	Product product = productRepository.findById(itemReq.getProductId())
        			.orElseThrow(() -> new RuntimeException("Product not found at id: " + itemReq.getProductId()));
        	
        	OrderItem item = new OrderItem();
        	item.setProduct(product);
        	item.setQuantity(itemReq.getQuantity());
        	item.setPriceAtPurchase(product.getPrice());
        	
        	order.addItem(item);
        	
        	total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity())));
        }
        
        order.setTotalPrice(total);
        
		orderRepository.save(order);
	}

	@Override
	public OrderDTO getById(Long id) {
		log.debug("get by id {}", id);
		return OrderMap.buildOrderDTO(orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found at id: " + id)));
	}
	
	@Override
	public List<OrderDTO> list() {
		log.debug("list");
		return OrderMap.buildOrderDTOList(orderRepository.findAll());
	}

	@Transactional
	@Override
	public void update(OrderReq req) throws Exception{
		Order order = orderRepository.findById(req.getId())
				.orElseThrow(() -> new Exception("Order not found at id: " + req.getId()));
		
		Optional.ofNullable(req.getStatus()).ifPresent(order::setStatus);
		
		orderRepository.save(order);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		log.debug("delete {}", id);
		orderRepository.deleteById(id);
	}
	
}
