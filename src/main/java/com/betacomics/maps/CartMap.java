package com.betacomics.maps;

import java.util.List;
import com.betacomics.dto.input.CartReq;
import com.betacomics.dto.output.CartDTO;
import com.betacomics.models.Cart;

public class CartMap {

    // Da Entity a DTO (Output)
    public static CartDTO buildCartDTO(Cart cart) {
        if (cart == null) return null;

        return CartDTO.builder()
                .id(cart.getId())
                .items(CartItemMap.buildCartItemDTOList(cart.getItems()))
                .build();
    }

    public static Cart toEntity(CartReq req) {
        if (req == null) return null;

        return Cart.builder()
                .id(req.getId())
                .build();
    }
}
