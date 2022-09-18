package com.company.service.impl;

import com.company.data.entity.BooksInCart;
import com.company.data.entity.Cart;
import com.company.data.repository.BookRep;
import com.company.data.repository.BooksInCartRep;
import com.company.data.repository.CartRep;
import com.company.service.CartService;
import com.company.service.dto.BookDto;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("cartService")
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final Logger log = LogManager.getLogger(CartServiceImpl.class);
    private final BookRep bookRep;
    private final ObjectMapperSC mapper;
    private  final CartRep cartRep;
    private final BooksInCartRep booksInCartRep;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public Cart findCartByUserId(UserDto userDto, HttpSession session) {
        return cartRep.findByUserId(userDto.getId());
    }

    @Override
    public boolean isCreatedCart(UserDto userDto) {
        if(cartRep.findByUserId(userDto.getId()) != null){
            return true;
        }
        return false;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public void deleteBookIntoCart(Long bookId) {
        if (cartRep.delete(bookId)) {
            log.debug("Start CartService - deleteCartById: {}", bookId);
        } else {
            log.error("Start CartService - deleteCartById false: {}", bookId);
            throw new RuntimeException("DeleteBook - Book is not exist...");
        }
    }

    @Override
    public Cart create(Cart cart) {
        return null;
    }

    @Override
    public Cart create(UserDto userDto, Map<Long, Integer> cartMap) {
        Cart cart = new Cart();
        BooksInCart booksInCart = addBooksInCart(cartMap);
        cart.setBooksInCart(booksInCart);
        cart = cartRep.create(cart);
        cart.setUserC(mapper.toUser(userDto));
        return cartRep.update(cart);
    }

    private BooksInCart addBooksInCart(Map<Long, Integer> cartMap) {
        BooksInCart booksInCart = new BooksInCart();
        if(cartMap.get(1) != null) {
            booksInCart.setBooks(mapper.toBook(findBooksByCart(cartMap).get(0)));//fixMe
        }
        booksInCart.setQuantity(1);
        return booksInCartRep.create(booksInCart);
    }

    @Override
    public Cart update(Cart cart) {
        return cartRep.update(cart);
    }

    @Override
    public void addProduct(Map<Long, Integer> cart, Long id, Integer quality) {
        log.debug("Start CartService - addProduct");
        if(id!=null && quality != null){
            cart.put(id, quality);
        }else {
            throw new RuntimeException("No Login or id from cartMap");
        }
    }

    @Override
    public boolean removeProduct(Map<Long, Integer> cart, Long id, Integer quality) {
        log.debug("Start CartService - removeProduct");
        return cart.remove(id, quality);
    }

    @Override
    public List<BookDto> findBooksByCart(Map<Long, Integer> cart) {
        log.debug("Start CartService - findBooksByCart");
        List<BookDto> bookDtoList = new ArrayList<>();
        Iterator<Map.Entry<Long, Integer>> iterator = cart.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Long, Integer> entry = iterator.next();
            bookDtoList.add(mapper.toBookDTO(bookRep.findById(entry.getKey())));
        }
        return bookDtoList;
    }
}
