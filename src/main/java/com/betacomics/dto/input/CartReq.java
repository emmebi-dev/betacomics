package com.betacomics.dto.input;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartReq {
    
    @NotNull(groups = ValidationGroup.Update.class, message = "Cart cannot be updated without valid id")
    private Long id;

    @Valid
    private List<CartItemReq> items;
}
