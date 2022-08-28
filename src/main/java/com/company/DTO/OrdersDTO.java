package com.company.DTO;

import com.company.entity.OrdersItems;
import com.company.entity.StatusBook;
import com.company.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@Data
public class OrdersDTO {
    private Long id;
    private User user;
    private BigDecimal totalCost;
    private LocalDate timestamp;
    private StatusBook status;
    private List<OrdersItems> items;
}
