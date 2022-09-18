package com.company.service;

import com.company.service.dto.BookDto;
import com.company.service.dto.OrdersDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public interface OrdersService extends AbstractService<OrdersDto> {
    OrdersDto create(BookDto bookDto, HttpSession session);
}
