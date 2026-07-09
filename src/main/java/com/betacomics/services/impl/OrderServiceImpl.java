package com.betacomics.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.betacomics.dto.input.OrderItemReq;
import com.betacomics.dto.input.OrderReq;
import com.betacomics.dto.output.OrderDTO;
import com.betacomics.maps.CartMap;
import com.betacomics.maps.OrderMap;
import com.betacomics.models.Cart;
import com.betacomics.models.Order;
import com.betacomics.models.OrderItem;
import com.betacomics.models.Product;
import com.betacomics.repositories.OrderRepository;
import com.betacomics.repositories.ProductRepository;
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
		
	@Transactional
	@Override
	public void create(OrderReq req) {
		log.debug("create Order {}", req);
        Order order = new Order();
        
        // TO-DO
        
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
	public void update(OrderReq req) {
		// TO-DO
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		log.debug("delete {}", id);
		orderRepository.deleteById(id);
	}
	
}
