package com.company.controller.impl.orders;

import com.company.controller.Command;
import com.company.service.OrdersItemsService;
import com.company.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("find_order_by_id")
@RequiredArgsConstructor
public class OrdersFindById implements Command {
    private static final Logger log = LogManager.getLogger(OrdersFindById.class);
    private final OrdersService ordersService;
    private final OrdersItemsService ordersItemsService;


    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start OrdersFindById {}", req.getParameter("id"));
        req.setAttribute("order", ordersService.findById(Long.parseLong(req.getParameter("id"))));
        req.setAttribute("order_item", ordersItemsService.findById(Long.parseLong(req.getParameter("id"))));
        return "JSP/order.jsp";
    }
}
