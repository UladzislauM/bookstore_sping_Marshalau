package com.company.service.serviceImpl;

import com.company.entity.Book;
import com.company.data.repository.BookRepJdbc;
import com.company.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private final BookRepJdbc bookRepJdbc;
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    public BookServiceImpl(BookRepJdbc bookRepJdbc) {
        this.bookRepJdbc = bookRepJdbc;
    }

    public void validate(Book book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Price is not valid, ...");
        }
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepJdbc.findAll();
        log.debug("Start BookService - getAllBooks - {}", books.size());
        return books;
    }

    @Override
    public Book findById(Long id) {
        log.debug("Start BookService - getBookById {}", id);
        return bookRepJdbc.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (bookRepJdbc.delete(id)) {
            log.debug("Start BookService - deleteBookById: {}", id);
        } else {
            log.error("BookService - deleteBookById false: {}", id);
        }
    }

    @Override
    public Book create(Book book) {
        log.debug("Start BookService - createBook {}", book);
        return bookRepJdbc.create(book);
    }

    @Override
    public Book update(Book book) {
        log.debug("Start BookService - updateBookById {}", book);
        return bookRepJdbc.update(book);
    }

    @Override
    public Long countAll() {
        log.debug("Start BookService - countAllBooks");
        return bookRepJdbc.countAll();
    }
}
