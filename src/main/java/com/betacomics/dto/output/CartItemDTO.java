package com.betacomics.dto.output;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemDTO {

    private Long id;
    private ProductDTO product; 
    private Integer quantity;
    
    private BigDecimal subTotal; 
}