package com.betacomics.dto.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.betacomics.enums.OrderStatus;

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
public class OrderDTO {

    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private List<OrderItemDTO> items;
    private Long userId;
    
}
