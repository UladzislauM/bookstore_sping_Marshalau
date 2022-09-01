package com.company.service.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@Data
public class Orders {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private LocalDate timestamp;
    private StatusBook status;
    private List<OrdersItems> items;
}
