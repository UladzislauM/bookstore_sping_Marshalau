package com.company.controller.impl;

import com.company.service.OrdersItemsService;
import com.company.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger log = LogManager.getLogger(OrderController.class);
    private final OrdersService ordersService;
    private final OrdersItemsService ordersItemsService;

    @GetMapping("/find_order_by_id/{id}")
    public String findOrder(@PathVariable Long id, Model model) {
        log.info("Start findOrder {}", model);
        model.addAttribute("order", ordersService.findById(id));
        model.addAttribute("order_item", ordersItemsService.findById(id));
        return "order";
    }

    @GetMapping("/orders_find")
    public String findOrders(Model model) {
        log.info("Start findOrders {}", model);
        model.addAttribute("orders", ordersService.findAll());
        return "orders";
    }
}
