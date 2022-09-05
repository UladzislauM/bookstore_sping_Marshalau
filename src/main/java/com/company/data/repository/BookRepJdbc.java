package com.company.data.repository;

import com.company.data.entity.Books;
import org.springframework.stereotype.Component;


@Component
public interface BookRepJdbc extends AbstractRepJdbc<Books> {
    Books create(Books books);

    Long countAll();
}
