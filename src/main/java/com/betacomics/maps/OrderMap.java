package com.betacomics.maps;

import java.util.List;

import com.betacomics.dto.output.OrderDTO;
import com.betacomics.models.Order;

public class OrderMap {
	public static List<OrderDTO> buildOrderDTOList(List<Order> lO){
		return lO.stream()
				.map(o -> buildOrderDTO(o)
						).toList();
	};
	
	public static OrderDTO buildOrderDTO(Order o) {
		return OrderDTO.builder()
				.id(o.getId())
				.userId(o.getUser() != null ? o.getUser().getId() : null)
				.orderDate(o.getOrderDate())
				.totalPrice(o.getTotalPrice())
				.status(o.getStatus())
				.items(OrderItemMap.buildOrderItemDTOList(o.getItems()))
				.build();
	}
}
