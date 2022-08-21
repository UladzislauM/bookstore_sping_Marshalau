package com.company.service;

import com.company.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {
    List<Book> getAllBooks();

    Object getBookById(Long id);

    void deleteBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Book book);

//    Book getBookByISBN(String isbn);
//
//    List<Book> getBookByAuthor(String author);
//
//    Long countAllBooks();
//
//    BigDecimal sumBooksByAuthor(String author);
//
//    void validate(Book book);
}
