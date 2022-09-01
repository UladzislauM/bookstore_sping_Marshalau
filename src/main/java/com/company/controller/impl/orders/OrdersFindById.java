package com.company.controller.impl.orders;

import com.company.service.dto.OrdersDto;
import com.company.controller.Command;
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

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start OrdersFindById {}", req.getParameter("id"));
        OrdersDto ordersDTO = ordersService.findById(Long.parseLong(req.getParameter("id")));
        req.setAttribute("order", ordersDTO);
        return "JSP/order.jsp";
    }
}
