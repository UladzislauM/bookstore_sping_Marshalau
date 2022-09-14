package com.company.data.repository;

import com.company.data.entity.Books;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface BookRep extends AbstractRep<Books> {
    Books create(Books books);

    Long countAll();

    Long countAllAuthors();

    List<Books> findByAuthor(String author);
}
