package com.company.web.controller.impl;

import com.company.web.controller.resurses.CartRes;
import com.company.data.entity.StatusBook;
import com.company.service.OrderItemService;
import com.company.service.OrderService;
import com.company.service.dto.OrderDto;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger log = LogManager.getLogger(OrderController.class);
    private final OrderService orderService;
    private final CartRes cartRes;

    @GetMapping("/find_order_by_id/{id}")
    public String findOrder(@PathVariable Long id, Model model) {
        log.info("Start findOrder {}", model);
        model.addAttribute("order", orderService.findById(id));
        return "order";
    }

    @GetMapping("/orders_find")
    public String findOrders(Model model) {
        log.info("Start findOrders {}", model);
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @PostMapping("/order_formation")
    public String addToOrder(HttpSession session, Model model) {
        log.info("Start addToOrder");
        Map<Long, Integer> cartMap = cartRes.getCart(session);
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto == null) {
            return "redirect:/login";
        }
        orderService.create(session, cartMap);
        model.addAttribute("orders", orderService.findByUserId(userDto.getId()));
        return "orders_history";
    }

    @PostMapping("/find_orders")
    public String findOrders(HttpSession session, Model model) {
        log.info("Start findOrders");
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto == null) {
            return "redirect:/login";
        }
        model.addAttribute("orders", orderService.findByUserId(userDto.getId()));
        return "orders_history";
    }

    @PostMapping("/confirm_order")
    public String confirmOrder(@PathVariable Long id_order, HttpSession session, Model model) {
        log.info("Start confirmOrder");
        UserDto userDto = (UserDto) session.getAttribute("user");
        OrderDto orderDto = orderService.findById(id_order);
        orderDto.setStatus(StatusBook.AWAITING_PAYMENT);
        orderService.update(orderDto);
        model.addAttribute("orders", orderService.findByUserId(userDto.getId()));
        return "orders_history";
    }
}
