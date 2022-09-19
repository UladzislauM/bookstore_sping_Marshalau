package com.company.data.repository;

import com.company.data.entity.Order;
import com.company.service.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrdersRep extends AbstractRep<Order> {
    List<Order> findByUserId(Long id);
}
