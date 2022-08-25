package com.company.controller.OrdersControllers;

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
    private final OrdersService ordersService;

    private static final Logger log = LogManager.getLogger(OrdersCommand.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start OrdersCommand {}", req);
        try {
            req.setAttribute("orders", ordersService.findAll());
            return "JSP/orders.jsp";
        } catch (Exception e) {
            log.error("Exception by OrdersCommand {}", e);
            req.setAttribute("errorMessage", "Ops..... The order does not exist: " + e);
            return "error.jsp";
        }
    }
}
