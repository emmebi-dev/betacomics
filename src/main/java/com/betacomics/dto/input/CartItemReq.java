package com.betacomics.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemReq {

    @NotNull(groups = ValidationGroup.Update.class, message = "CartItem cannot be updated without valid id")
    private Long id;

    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Cart ID is required")
    private Long cartId;

    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Product ID is required")
    private Long productId;

    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Quantity is required")
    @Positive(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Quantity must be greater than 0")
    private Integer quantity;
}
