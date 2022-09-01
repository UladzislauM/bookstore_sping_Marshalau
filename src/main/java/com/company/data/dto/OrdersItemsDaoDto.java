package com.company.data.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class OrdersItemsDaoDto {
    private Long id;
    private Long order_id;
    private Long book_id;
    private Integer quantity;
    private BigDecimal price;
}
