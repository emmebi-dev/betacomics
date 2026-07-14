package com.betacomics.dto.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.betacomics.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private List<OrderItemDTO> items;
}