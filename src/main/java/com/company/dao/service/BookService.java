package com.company.dao.service;

import com.company.dao.entity.Book;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface BookService {
    List<Book> getAllBooks();

    Object getBookById(Long id);

    void deleteBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Book book);

    Book getBookByISBN(String isbn);

    List<Book> getBookByAuthor(String author);

    Long countAllBooks();

    BigDecimal sumBooksByAuthor(String author);

    void validate(Book book);
}
