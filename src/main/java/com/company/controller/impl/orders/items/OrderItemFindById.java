package com.company.controller.impl.orders.items;

import com.company.controller.Command;
import com.company.service.OrdersItemsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("order_item_find_by_id")
@RequiredArgsConstructor
public class OrderItemFindById implements Command {
    private static final Logger log = LogManager.getLogger(OrderItemFindById.class);
    private final OrdersItemsService ordersItemsService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start OrdersFindById {}", req.getParameter("id"));
        req.setAttribute("order_item", ordersItemsService.findById(Long.parseLong(req.getParameter("id"))));
        return "JSP/orderItem.jsp";
    }
}
