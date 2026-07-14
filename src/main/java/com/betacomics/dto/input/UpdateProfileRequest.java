package com.betacomics.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateProfileRequest {

    @NotBlank(message = "Lo username è obbligatorio")
    @Size(min = 3, max = 50, message = "Lo username deve essere compreso tra 3 e 50 caratteri")
    private String username;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Inserisci un indirizzo email valido")
    private String email;
}