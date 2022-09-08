package com.company.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrdersItemsDTO {
    private Long id;
    private OrdersDto ordersDto;
    private BookDto bookDto;
    private Integer quantity;
    private BigDecimal price;
}
