package com.company.controller.impl.orders;

import com.company.controller.Command;
import com.company.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("all_orders")
@RequiredArgsConstructor
public class OrdersCommand implements Command {
    private static final Logger log = LogManager.getLogger(OrdersCommand.class);
    private final OrdersService ordersService;


    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start OrdersCommand {}", req);
        req.setAttribute("orders", ordersService.findAll());
        return "JSP/orders.jsp";
    }
}
