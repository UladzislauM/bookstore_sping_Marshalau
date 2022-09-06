package com.company.data.repository;

import com.company.data.entity.Books;
import org.springframework.stereotype.Component;


@Component
public interface BookRep extends AbstractRep<Books> {
    Books create(Books books);

    Long countAll();
}
