package com.betacomics.maps;

import com.betacomics.dto.input.CartReq;
import com.betacomics.dto.output.CartDTO;
import com.betacomics.models.Cart;
import com.betacomics.models.User;

public class CartMap {

    // Da Entity a DTO (Output)
    public static CartDTO buildCartDTO(Cart cart) {
        if (cart == null) return null;

        return CartDTO.builder()
                .id(cart.getId())
                .user(cart.getUser())
                .items(CartItemMap.buildCartItemDTOList(cart.getItems()))
                .build();
    }

    public static Cart toEntity(CartReq req, User user) throws Exception {
        if (req == null) return null;

        return Cart.builder()
                .id(req.getId())
                .user(user)
                .build();
    }
}
