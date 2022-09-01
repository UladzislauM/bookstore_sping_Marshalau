package com.company.data.repository;

import com.company.service.entity.OrdersItems;
import org.springframework.stereotype.Component;

@Component
public interface OrderItemRepJdbc extends AbstractRepJdbc<OrdersItems> {
    OrdersItems create(OrdersItems entity, Long id);
}
