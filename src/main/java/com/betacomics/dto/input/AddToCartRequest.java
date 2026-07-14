package com.betacomics.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddToCartRequest {

    @NotNull(message = "L'id del prodotto è obbligatorio")
    private Long productId;

    @NotNull(message = "La quantità è obbligatoria")
    @Positive(message = "La quantità deve essere maggiore di zero")
    private Integer quantity;
}