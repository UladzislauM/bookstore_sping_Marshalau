package com.company.data.repository;

import com.company.data.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemRep extends AbstractRep<OrderItem> {

    List<OrderItem> findByOrdersId(Long id);
}
