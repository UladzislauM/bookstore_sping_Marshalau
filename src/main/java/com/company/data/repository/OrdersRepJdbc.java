package com.company.data.repository;

import com.company.service.entity.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersRepJdbc extends AbstractRepJdbc<Orders> {
    Orders create(Orders entity);
}
