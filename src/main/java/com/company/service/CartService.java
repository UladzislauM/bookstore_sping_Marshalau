package com.company.service;

import com.company.data.entity.Cart;
import com.company.service.dto.BookDto;
import com.company.service.dto.UserDto;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CartService extends AbstractService<Cart> {
    void addProduct(Map<Long, Integer> cart, Long id, Integer quality);

    void removeProduct(Map<Long, Integer> cart, Long id, Integer quality);

    List<BookDto> findBooksByCart(Map<Long, Integer> cart);

    Cart findCartByUserId(UserDto userDto, HttpSession session);

    boolean isCreatedCart(UserDto userDto);

    Cart create(UserDto userDto, Map<Long, Integer> cartMap);

    void deleteBookIntoCart(Long bookId);

    Map<Long, Integer> sumPrice(BigDecimal price, Long id, Map<Long, Integer> cartMap, Boolean plus,
            HttpSession session);

    void sumPriceInCart(BigDecimal price, HttpSession session, boolean sum, int quantity);

    Integer plusQuantity(Long id, Map<Long, Integer> cartMap);

    Map<Long, Integer> getCart(HttpSession session);
}
