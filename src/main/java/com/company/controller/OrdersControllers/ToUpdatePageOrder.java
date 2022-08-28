package com.company.controller.OrdersControllers;

import com.company.controller.BookControllers.BookDelete;
import com.company.controller.Command;
import com.company.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("order_update_form")
@RequiredArgsConstructor
public class ToUpdatePageOrder implements Command {
    private final OrdersService ordersService;

    private static final Logger log = LogManager.getLogger(BookDelete.class);

    @Override
    public String execude(HttpServletRequest req) {
        log.info("Start ToUpdatePageOrder {}", req.getParameter("id"));
        try {
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("order", ordersService.findById(Long.parseLong(req.getParameter("id"))));
            return "JSP/updateOrder.jsp";
        } catch (Exception e) {
            log.error("Exception by ToUpdatePageOrder {}", e);
            req.setAttribute("errorMessage", "Ops..... The order does not update: " + e);
            return "error.jsp";
        }
    }
}
