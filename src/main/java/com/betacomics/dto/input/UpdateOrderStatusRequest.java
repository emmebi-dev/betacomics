package com.betacomics.dto.input;

import com.betacomics.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
public class UpdateOrderStatusRequest {

    @NotNull(message = "Lo stato dell'ordine è obbligatorio")
    private OrderStatus status;
}