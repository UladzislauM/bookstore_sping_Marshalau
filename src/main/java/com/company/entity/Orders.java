package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class Orders {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private LocalDate timestamp;
    private StatusBook status;
}
