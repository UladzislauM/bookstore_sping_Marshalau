package com.company.web.controller.resurses;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@Component
public class CartRes {
    public Map<Long, Integer> getCart(HttpSession session) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        return cart;
    }

    public Integer plusQuantity(Long id, Map<Long, Integer> cartMap) {
        Integer quantity = cartMap.get(id);
        if (quantity == null) {
            quantity = 1;
        } else if (quantity >= 1) {
            quantity++;
        }
        return quantity;
    }
}
