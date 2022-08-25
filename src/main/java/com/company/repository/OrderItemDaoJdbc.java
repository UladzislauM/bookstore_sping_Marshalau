package com.company.repository;

import com.company.entity.OrdersItems;
import org.springframework.stereotype.Component;

@Component
public interface OrderItemDaoJdbc extends AbstractDaoJdbc<OrdersItems> {
    OrdersItems create(OrdersItems entity, Long id);
}
