package com.betacomics.dto.output;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemDTO {
   
	private Long id;
	private ProductDTO product;
	private BigDecimal priceAtPurchase;
	private Integer quantity;
    private Long orderId;
}
