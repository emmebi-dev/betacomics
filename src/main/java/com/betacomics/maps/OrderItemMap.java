package com.betacomics.maps;

import java.util.List;

import com.betacomics.dto.input.OrderItemReq;
import com.betacomics.dto.output.OrderItemDTO;
import com.betacomics.models.Order;
import com.betacomics.models.OrderItem;
import com.betacomics.models.Product;

public class OrderItemMap {
	
    public static OrderItemDTO buildOrderItemDTO(OrderItem item) {
        if (item == null) return null;

        return OrderItemDTO.builder()
                .id(item.getId())
                .orderId(item.getOrder() != null ? item.getOrder().getId() : null)
                .product(ProductMap.buildProductDTO(item.getProduct()))
                .quantity(item.getQuantity())
                .build();
    }

    public static List<OrderItemDTO> buildOrderItemDTOList(List<OrderItem> list) {
        if (list == null) return List.of();
        return list.stream()
                .map(OrderItemMap::buildOrderItemDTO)
                .toList();
    }
    
	
	public static OrderItem toEntity(OrderItemReq req, Order order, Product product) {
        if (req == null) return null;

        return OrderItem.builder()
                .id(req.getId())
                .product(product)
                .priceAtPurchase(req.getPriceAtPurchase())
                .quantity(req.getQuantity())
                .order(order)
                .build();
    }

}
