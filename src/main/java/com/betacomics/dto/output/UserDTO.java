package com.betacomics.dto.output;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private boolean isAdmin;
}