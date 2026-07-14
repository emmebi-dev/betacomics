package com.betacomics.dto.output;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private BigDecimal priceAtPurchase;
    private Integer quantity;
    
    private BigDecimal subTotal; 
}