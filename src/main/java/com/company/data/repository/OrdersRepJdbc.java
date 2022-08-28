package com.company.data.repository;

import com.company.entity.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersRepJdbc extends AbstractRepJdbc<Orders> {
    Orders create(Orders entity);
}
