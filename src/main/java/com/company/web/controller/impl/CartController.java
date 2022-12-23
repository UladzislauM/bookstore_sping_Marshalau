package com.company.web.controller.impl;

import com.company.service.BookService;
import com.company.service.CartService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private static final Logger log = LogManager.getLogger(CartController.class);
    private final CartService cartService;
    private final BookService bookService;

    @PostMapping("/get_cart")
    public String toCart(HttpSession session, Model model) {
        log.info("Start toCart");
        Map<Long, Integer> cart = cartService.getCart(session);
        UserDto userDto = (UserDto) session.getAttribute("user");
        model.addAttribute("user", userDto);
        model.addAttribute("books", cartService.findBooksByCart(cart));
        model.addAttribute("quantity", cart);
        return "cart";
    }

    @PostMapping("/book_to_cart")
    public String bookToCart(@ModelAttribute("id") Long id, HttpSession session) {
        log.info("Start bookToCart {}", id);
        Map<Long, Integer> cartMap = cartService.getCart(session);
        int quantity = cartService.plusQuantity(id, cartMap);
        BigDecimal price = bookService.findById(id).getPrice();
        cartService.sumPriceInCart(price, session, true, 1);
        session.setAttribute("cart", cartMap);
        cartService.addProduct(cartMap, id, quantity);
        return "redirect:/books/books_find";
    }

    @PostMapping("/book_delete")
    public String bookDelete(@RequestParam Long id, HttpSession session) {
        log.info("Start bookDelete {}", id);
        Map<Long, Integer> cartMap = cartService.getCart(session);
        int quantity = cartMap.get(id);
        session.setAttribute("cart", cartMap);
        BigDecimal price = bookService.findById(id).getPrice();
        cartService.sumPriceInCart(price, session, false, quantity);
        cartService.removeProduct(cartMap, id, quantity);
        return "forward:/cart/get_cart";
    }

    @PostMapping("/book_q")
    public String quantityBooks(@RequestParam Long id, Boolean plus, HttpSession session) {
        log.info("Start quantityBooks {}", id);
        Map<Long, Integer> cartMap = cartService.getCart(session);
        BigDecimal price = bookService.findById(id).getPrice();
        cartService.sumPrice(price, id, cartMap, plus, session);
        session.setAttribute("cart", cartMap);
        return "forward:/cart/get_cart";
    }
}
