package com.company.service;

import com.company.DTO.OrdersDTO;
import com.company.entity.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersService extends AbstractService<Orders, OrdersDTO> {
}
