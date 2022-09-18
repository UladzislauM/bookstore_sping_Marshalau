package com.company.service;

import com.company.service.dto.OrdersDto;
import com.company.service.dto.OrdersItemsDTO;

import java.util.List;

public interface OrdersItemsService extends AbstractService<OrdersItemsDTO> {
    List<OrdersItemsDTO> findByOrdersId(Long id);

    OrdersItemsDTO create(OrdersDto ordersDto);
}
