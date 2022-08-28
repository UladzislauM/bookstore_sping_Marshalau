package com.company.data.repository;

import com.company.entity.Book;
import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserRepJdbc extends AbstractRepJdbc<User> {
    User create(User user);

    Long countAll();
}
