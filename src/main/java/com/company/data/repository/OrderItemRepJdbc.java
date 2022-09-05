package com.company.data.repository;

import com.company.data.entity.OrdersItems;
import org.springframework.stereotype.Component;

@Component
public interface OrderItemRepJdbc extends AbstractRepJdbc<OrdersItems> {
    OrdersItems create(OrdersItems entity, Long id);
}
