package com.company.web.controller.impl;

import com.company.web.controller.resurses.CartRes;
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
    private final CartRes cartRes;

    @PostMapping("/get_cart")
    public String toCart(HttpSession session, Model model) {
        log.info("Start toCart");
        Map<Long, Integer> cart = cartRes.getCart(session);
        UserDto userDto = (UserDto) session.getAttribute("user");
        model.addAttribute("user", userDto);
        model.addAttribute("books", cartService.findBooksByCart(cart));
        model.addAttribute("quantity", cart);
        return "cart";
    }

    @PostMapping("/book_to_cart")
    public String bookToCart(@ModelAttribute("id") Long id, HttpSession session) {
        log.info("Start bookToCart {}", id);
        Map<Long, Integer> cartMap = cartRes.getCart(session);
        int quantity = cartRes.plusQuantity(id, cartMap);
        session.setAttribute("cart", cartMap);
        cartService.addProduct(cartMap, id, quantity);
        sumPrice(id, session, true, quantity);
        return "redirect:/books/books_find";
    }


    @PostMapping("/book_delete")
    public String bookDelete(@RequestParam Long id, HttpSession session) {
        log.info("Start bookDelete {}", id);
        Map<Long, Integer> cartMap = cartRes.getCart(session);
        int quantity = cartMap.get(id);
        session.setAttribute("cart", cartMap);
        sumPrice(id, session, false, quantity);
        cartService.removeProduct(cartMap, id, quantity);
        return "forward:/cart/get_cart";
    }

    @PostMapping("/book_q")
    public String quantityBooks(@RequestParam Long id, Boolean plus, HttpSession session) {
        log.info("Start quantityBooks {}", id);
        Map<Long, Integer> cartMap = cartRes.getCart(session);
        int quantity;
        if (plus) {//Fixme
            quantity = cartMap.get(id) + 1;
            sumPrice(id, session, true, 1);
        } else {
            quantity = cartMap.get(id) - 1;
            sumPrice(id, session, false, 1);
            if (quantity <= 0) {
                return bookDelete(id, session);
            }
        }
        cartService.addProduct(cartMap, id, quantity);
        session.setAttribute("cart", cartMap);
        return "forward:/cart/get_cart";
    }

    private void sumPrice(Long id, HttpSession session, boolean sum, int quantity) {
        BigDecimal price = bookService.findById(id).getPrice()
                .multiply(BigDecimal.valueOf(quantity));
        if (session.getAttribute("total_cost_cart") == null) {//fixme
            session.setAttribute("total_cost_cart", price);
        } else {
            BigDecimal totalPrice = (BigDecimal) session.getAttribute("total_cost_cart");
            session.setAttribute("total_cost_cart",
                    cartService.sumPrice(price, totalPrice, sum));
        }
    }
}
