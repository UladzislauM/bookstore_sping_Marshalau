package com.company.controller.impl.orders;

import com.company.controller.impl.book.BookDelete;
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
    private static final Logger log = LogManager.getLogger(BookDelete.class);
    private final OrdersService ordersService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start ToUpdatePageOrder {}", req.getParameter("id"));
        req.setAttribute("order", ordersService.findById(Long.parseLong(req.getParameter("id"))));
        return "JSP/updateOrder.jsp";
    }
}
