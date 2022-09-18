package com.company.data.repository;

import com.company.data.entity.Cart;

public interface CartRep extends AbstractRep<Cart>{
    Cart findByUserId(Long id);
    boolean deleteBookIntoCart(Long bookId);
}
