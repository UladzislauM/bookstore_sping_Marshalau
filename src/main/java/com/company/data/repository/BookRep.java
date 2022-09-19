package com.company.data.repository;

import com.company.data.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface BookRep extends AbstractRep<Book> {

    Long countAll();

    Long countAllAuthors();

    List<Book> findByAuthor(String author);
}
