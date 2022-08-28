package com.company.data.repository;

import com.company.entity.Book;
import org.springframework.stereotype.Component;

@Component
public interface BookRepJdbc extends AbstractRepJdbc<Book> {
    Book create(Book book);

    Long countAll();
}
