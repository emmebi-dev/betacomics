package com.betacomics.services.interfaces;

import java.util.List;

import com.betacomics.dto.input.OrderReq;
import com.betacomics.dto.output.OrderDTO;

public interface OrderService {
	public void create(OrderReq req);
	
	public OrderDTO getById(Long id);
	public List<OrderDTO> list();
	
	public void update(OrderReq req) throws Exception;
	
	public void delete(Long id);
}
