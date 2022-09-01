package com.company.data.dao;

import com.company.data.dto.OrdersDaoDto;
import org.springframework.stereotype.Component;

@Component
public interface OrdersDaoJdbc extends AbstractDaoJdbc<OrdersDaoDto> {
    OrdersDaoDto create(OrdersDaoDto entity);
}
