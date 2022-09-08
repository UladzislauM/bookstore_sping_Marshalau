package com.company.data.repository;

import com.company.data.entity.OrdersItems;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemRep extends AbstractRep<OrdersItems> {
    OrdersItems create(OrdersItems entity, Long id);

    List<OrdersItems> findByOrdersId(Long id);
}
