package com.company.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private OrderDto orderDto;
    private BookDto bookDto;
    private Integer quantity;
    private BigDecimal price;
}
