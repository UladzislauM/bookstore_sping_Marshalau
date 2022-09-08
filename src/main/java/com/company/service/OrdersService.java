package com.company.service;

import com.company.service.dto.OrdersDto;
import com.company.data.entity.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersService extends AbstractService<Orders, OrdersDto> {
}
