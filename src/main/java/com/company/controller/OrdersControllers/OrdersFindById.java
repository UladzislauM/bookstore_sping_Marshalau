package com.company.controller.OrdersControllers;

import com.company.controller.Command;
import com.company.entity.Orders;
import com.company.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("find_order_by_id")
@RequiredArgsConstructor
public class OrdersFindById implements Command {
    private final OrdersService ordersService;
    private Orders order;

    private static final Logger log = LogManager.getLogger(OrdersFindById.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start OrdersFindById {}", req.getParameter("id"));
        try {
            order = ordersService.findById(Long.parseLong(req.getParameter("id")));
            if (order.getId() == 0) {
                log.error("The book does not exist, OrdersFindById");
                req.setAttribute("errorMessage", "The book does not exist, OrdersFindById");
                return "error.jsp";
            } else {
                req.setAttribute("order", order);
                return "JSP/order.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by OrdersFindById {}", e);
            req.setAttribute("errorMessage", "The book does not exist, OrdersFindById: " + e);
            return "error.jsp";
        }
    }
}
