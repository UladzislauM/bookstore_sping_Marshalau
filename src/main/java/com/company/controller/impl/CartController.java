package com.company.controller.impl;

import com.company.service.CartService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private static final Logger log = LogManager.getLogger(CartController.class);
    private final CartService cartService;

    @PostMapping("/get_cart")
    public String toCart(HttpSession session, Model model) {
        log.info("Start toCart");
        Map<Long, Integer> cart = getCart(session);
        UserDto userDto = (UserDto) session.getAttribute("user");
        model.addAttribute("user", userDto);
        model.addAttribute("books", cartService.findBooksByCart(cart));
        model.addAttribute("quantity", cart);
        return "cart";
    }

    @PostMapping("/book_to_cart")
    public String bookToCart(@ModelAttribute("id") Long id, HttpSession session) {
        log.info("Start bookToCart {}", id);
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Map<Long, Integer> cartMap = getCart(session);
        cartService.addProduct(cartMap, id, 1);
        session.setAttribute("cart", cartMap);
        return "redirect:/books/books_find";
    }

    @PostMapping("/book_delete")
    public String bookDelete(@RequestParam Long id, HttpSession session) {
        log.info("Start bookToCart {}", id);
        Map<Long, Integer> cartMap = getCart(session);
        cartService.removeProduct(cartMap, id, 1);
        session.setAttribute("cart", cartMap);
        return "forward:/cart/get_cart";
    }

    private Map<Long, Integer> getCart(HttpSession session) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        return cart;
    }
}
