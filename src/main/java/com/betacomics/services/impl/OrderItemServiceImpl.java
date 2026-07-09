package com.betacomics.services.impl;

import com.betacomics.dto.input.OrderItemReq;
import com.betacomics.repositories.OrderItemRepository;
import com.betacomics.repositories.OrderRepository;
import com.betacomics.repositories.ProductRepository;
import com.betacomics.services.interfaces.OrderItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {
	
	private final OrderItemRepository orderItemRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	

	@Override
	public void addItem(OrderItemReq req) {
		// TODO Auto-generated method stub
    }

	@Override
	public void updateQuantity(OrderItemReq req) {
		// TODO Auto-generated method stub
		
	}

}
