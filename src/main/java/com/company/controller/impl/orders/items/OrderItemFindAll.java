package com.company.controller.impl.orders.items;

import com.company.controller.Command;
import com.company.service.OrdersItemsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("all_orderItems")
@RequiredArgsConstructor
public class OrderItemFindAll implements Command {
    private static final Logger log = LogManager.getLogger(OrderItemFindAll.class);
    private final OrdersItemsService ordersItemsService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start OrderItemFindAll {}", req);
        req.setAttribute("order_items", ordersItemsService.findAll());
        return "JSP/orderItems.jsp";
    }
}
