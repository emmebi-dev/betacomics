package com.betacomics.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String accessToken;

    @Builder.Default
    private String tokenType = "Bearer";

    private String username;

    private String role;
}