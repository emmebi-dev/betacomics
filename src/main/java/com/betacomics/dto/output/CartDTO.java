package com.betacomics.dto.output;

import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDTO {
    
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;
    
    private BigDecimal totalCartPrice; 
}