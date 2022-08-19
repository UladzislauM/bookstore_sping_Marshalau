package com.company.dao.dao;

import com.company.dao.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookDao extends AbstractDao<Book, Long> {
    Book findBookByISBN(String isbn);

    List<Book> findBooksByAuthor(String author);
}
