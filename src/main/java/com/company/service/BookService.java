package com.company.service;

import com.company.service.dto.BookDto;
import com.company.service.entity.Book;
import org.springframework.stereotype.Component;

@Component
public interface BookService extends AbstractService<Book, BookDto> {
    Long countAll();
}
