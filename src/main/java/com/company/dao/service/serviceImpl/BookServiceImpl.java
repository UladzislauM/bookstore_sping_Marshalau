package com.company.dao.service.serviceImpl;

import com.company.dao.entity.Book;
import com.company.dao.repository.BookDaoJdbc;
import com.company.dao.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    private final BookDaoJdbc bookDaoJdbc;
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    public BookServiceImpl(BookDaoJdbc bookDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
    }

    public void validate(Book book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Price is not valid, ...");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookDaoJdbc.findAll();
        log.debug("Start BookService - getAllBooks - {}", books.size());
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        log.debug("Start BookService - getBookById {}", id);
        return bookDaoJdbc.findById(id);
    }

    @Override
    public void deleteBookById(Long id) {
        if (bookDaoJdbc.delete(id)) {
            log.debug("Start BookService - deleteBookById: {}", id);
        } else {
            log.error("BookService - deleteBookById false: {}", id);
        }
    }

    @Override
    public Book createBook(Book book) {
        log.debug("Start BookService - createBook {}", book);
        return bookDaoJdbc.create(book);
    }

    @Override
    public Book updateBook(Book book) {
        log.debug("Start BookService - updateBookById {}", book);
        return bookDaoJdbc.update(book);
    }

//    @Override
//    public Book getBookByISBN(String isbn) {
//        log.debug("Start BookService - getBookByISBN {}", isbn);
//        return bookDaoJdbc.findBookByISBN(isbn);
//    }
//
//    @Override
//    public List<Book> getBookByAuthor(String author) {
//        log.debug("Start BookService - getBookByISBN {}", author);
//        List<Book> books = bookDaoJdbc.findBooksByAuthor(author);
//        return books;
//    }
//
//    @Override
//    public Long countAllBooks() {
//        log.debug("Start BookService - countAllBooks");
//        return bookDaoJdbc.countAll();
//    }
//
//    @Override
//    public BigDecimal sumBooksByAuthor(String author) {
//        log.debug("Start BookService - sumBooksByAuthor {}", author);
//        List<Book> books = bookDaoJdbc.findBooksByAuthor(author);
//        try {
//            if (books.size() != 0) {
//                BigDecimal sum = books.get(0).getPrice();
//                for (int i = 1; i < books.size(); i++) {
//                    sum = sum.add(books.get(i).getPrice());
//                }
//                return sum;
//            }
//        } catch (Exception e) {
//            log.error("Method error - sumBooksByAuthor: {}", e);
//        }
//        throw new RuntimeException("Method error - sumBooksByAuthor");
//    }
}
