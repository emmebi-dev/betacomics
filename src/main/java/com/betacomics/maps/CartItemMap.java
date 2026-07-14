package com.betacomics.maps;

import java.util.List;

import com.betacomics.dto.output.CartItemDTO;
import com.betacomics.models.CartItem;

public class CartItemMap {

    public static CartItemDTO buildCartItemDTO(CartItem item) {
        if (item == null) return null;

        return CartItemDTO.builder()
                .id(item.getId())
                //.cartId(item.getCart() != null ? item.getCart().getId() : null)
                .product(ProductMap.buildProductDTO(item.getProduct()))
                .quantity(item.getQuantity())
                .build();
    }

    public static List<CartItemDTO> buildCartItemDTOList(List<CartItem> list) {
        if (list == null) return List.of();
        return list.stream()
                .map(CartItemMap::buildCartItemDTO)
                .toList();
    }
}
