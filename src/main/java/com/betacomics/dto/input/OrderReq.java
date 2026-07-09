package com.betacomics.dto.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.betacomics.enums.OrderStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderReq {

    @NotNull(groups = ValidationGroup.Update.class, message = "Order cannot be updated without valid id")
    @Schema(description = "ID dell'ordine. Richiesto obbligatoriamente solo in fase di update.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;
	
    @NotNull(groups = ValidationGroup.Create.class, message = "Order cannot be created without valid order date")
    private LocalDateTime orderDate;
    
    @NotNull(groups = ValidationGroup.Create.class, message = "Order cannot be created without valid user id")
    private Long userId;
	
    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Total price is required")
    @PositiveOrZero(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "Total price must be positive or zero")
    private BigDecimal totalPrice;
	
    @NotNull(groups = ValidationGroup.Update.class, message = "Status is required for update")
    private OrderStatus status;
	
    @NotEmpty(groups = ValidationGroup.Create.class, message = "An order must contain at least one item")
    @Valid
    private List<OrderItemReq> orderItems;
    
    
}