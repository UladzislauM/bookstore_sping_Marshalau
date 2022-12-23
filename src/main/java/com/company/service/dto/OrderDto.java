package com.company.service.dto;

import com.company.data.entity.OrderItem;
import com.company.data.entity.User;
import lombok.Data;
import com.company.data.entity.StatusBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private LocalDate timestamp;
    private StatusBook status;
    private List<OrderItem> items;
}
