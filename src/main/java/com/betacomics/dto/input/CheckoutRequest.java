package com.betacomics.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckoutRequest {

    @NotBlank(message = "L'indirizzo di spedizione è obbligatorio")
    private String shippingAddress;

    @NotBlank(message = "Il metodo di pagamento è obbligatorio")
    private String paymentMethod; // Es. "STRIPE", "PAYPAL"
}