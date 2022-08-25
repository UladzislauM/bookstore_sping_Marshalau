package com.company.service;

import com.company.entity.Book;
import org.springframework.stereotype.Component;

@Component
public interface BookService extends AbstractService<Book> {
    Long countAll();
}
