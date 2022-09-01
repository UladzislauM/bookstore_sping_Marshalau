package com.company.service.dto;

import com.company.service.entity.OrdersItems;
import com.company.service.entity.StatusBook;
import com.company.service.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@Data
public class OrdersDto {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private LocalDate timestamp;
    private StatusBook status;
    private List<OrdersItems> items;
}
