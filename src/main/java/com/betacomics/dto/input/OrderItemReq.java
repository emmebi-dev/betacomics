package com.betacomics.dto.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class OrderItemReq {
	
    @NotNull(groups = ValidationGroup.Update.class, message = "OrderItem cannot be updated without valid id")
	private Long id;
    
    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Product ID is required")
    private Long productId;
    
    @NotNull(groups = ValidationGroup.Create.class, message = "User cannot be created without valid Cart")
    @Positive(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Quantity must be greater than 0")
	private BigDecimal priceAtPurchase;
    
    @NotNull(groups = ValidationGroup.Create.class, message = "User cannot be created without valid Cart")
    @Positive(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Quantity must be greater than 0")
	private Integer quantity;
    
    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Order ID is required")
    private Long orderId;
}
