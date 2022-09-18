package com.company.service;

import com.company.service.dto.BookDto;
import com.company.data.entity.Books;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService extends AbstractService<BookDto> {
    Long countAll();

    Long countAllAuthors();

    List<BookDto> findAllAuthors();

    List<BookDto> findByAuthor(String author);
}
