package com.company.controller.impl.orders;

import com.company.data.entity.StatusBook;
import com.company.service.dto.OrdersDto;
import com.company.controller.Command;
import com.company.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller("order_update")
@RequiredArgsConstructor
public class OrdersUpdate implements Command {
    private static final Logger log = LogManager.getLogger(OrdersUpdate.class);
    private final OrdersService ordersService;

    @Override
    public String execute(HttpServletRequest req) {
        log.info("Start OrdersUpdate {}", req.getParameter("id"));
        OrdersDto ordersDTO = ordersService.findById(Long.parseLong(req.getParameter("id")));
        ordersDTO = addOrderKeyBoard(req, ordersDTO);
        ordersService.update(ordersDTO);
        req.setAttribute("order", ordersDTO);
        return "JSP/order.jsp";
    }

    private OrdersDto addOrderKeyBoard(HttpServletRequest req, OrdersDto ordersDTO) {
        if (req.getParameter("status_name") != null) {
            ordersDTO.setStatus(StatusBook.valueOf(req.getParameter("status_name")));
        }
        return ordersDTO;
    }
}
