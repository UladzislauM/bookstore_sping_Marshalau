package com.company.controller.OrdersControllers;

import com.company.controller.Command;
import com.company.entity.Orders;
import com.company.entity.StatusBook;
import com.company.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("order_update")
@RequiredArgsConstructor
public class OrdersUpdate implements Command {
    private final OrdersService ordersService;
    private Orders orders;

    private static final Logger log = LogManager.getLogger(OrdersUpdate.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start OrdersUpdate {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            orders = ordersService.findById(Long.parseLong(req.getParameter("id")));
            orders = addOrderKeyBoard(req);
            if (orders == null) {
                log.error("The order does not update, OrdersUpdate");
                req.setAttribute("errorMessage", "Ops..... The order does not update, OrdersUpdate");
                return "error.jsp";
            } else {
                ordersService.update(orders);
                req.setAttribute("order", orders);
                return "JSP/order.jsp";
            }
        } catch (Exception e) {
            log.error("Exception by BookUpdate {}", e);
            req.setAttribute("errorMessage", "Ops..... The order does not update: " + e);
            return "error.jsp";
        }
    }

    private Orders addOrderKeyBoard(HttpServletRequest req) {
        if (req.getParameter("status_name") != null) {
            orders.setStatus(StatusBook.valueOf(req.getParameter("status_name")));
        }
        return orders;
    }
}
