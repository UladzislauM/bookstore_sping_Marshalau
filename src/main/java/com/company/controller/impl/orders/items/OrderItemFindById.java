package com.company.controller.impl.orders.items;

import com.company.controller.Command;
import com.company.controller.impl.orders.OrdersFindById;
import com.company.service.OrdersService;
import com.company.service.dto.OrdersDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("order_item_find_by_id")
@RequiredArgsConstructor
public class OrderItemFindById implements Command {
    private static final Logger log = LogManager.getLogger(OrdersFindById.class);
    private final OrdersService ordersService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start OrdersFindById {}", req.getParameter("id"));
        OrdersDto ordersDTO = ordersService.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("order", ordersDTO);
        return "JSP/order.jsp";
    }
}
