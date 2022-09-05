package com.company.data.repository;

import com.company.data.entity.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersRepJdbc extends AbstractRepJdbc<Orders> {
    Orders create(Orders entity);
}
