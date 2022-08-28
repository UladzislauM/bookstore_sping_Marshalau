package com.company.data.dao;

import com.company.data.dataDTO.OrdersDaoDTO;
import com.company.entity.Orders;
import org.springframework.stereotype.Component;

@Component
public interface OrdersDaoJdbc extends AbstractDaoJdbc<OrdersDaoDTO> {
    OrdersDaoDTO create(OrdersDaoDTO entity);
}
