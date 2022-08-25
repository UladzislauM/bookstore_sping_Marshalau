package com.company.repository;

import com.company.entity.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersDaoJdbc extends AbstractDaoJdbc<Orders> {
    Orders create(Orders entity);
}
