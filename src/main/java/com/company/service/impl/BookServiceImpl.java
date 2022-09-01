package com.company.service.impl;

import com.company.service.dto.BookDto;
import com.company.service.entity.Book;
import com.company.data.repository.BookRepJdbc;
import com.company.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("bookService")
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private final BookRepJdbc bookRepJdbc;
    private final ObjectMapperSC mapper;

    public void validate(Book book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Price is not valid, ...");
        }
    }

    @Override
    public List<BookDto> findAll() {
        log.debug("Start BookService - findAllBooks");
        List<Book> books = bookRepJdbc.findAll();
        if (books == null) {
            log.error("BookService - findAll - Books is not exist");
            throw new RuntimeException("FindAll - Books is not exist...");
        } else {
            List<BookDto> booksDTO = books.stream().map(book -> {
                return mapper.toBookDTO(book);
            }).toList();
            return booksDTO;
        }
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Start BookService - getBookById {}", id);
        BookDto bookDTO = mapper.toBookDTO(bookRepJdbc.findById(id));
        if (bookDTO == null) {
            log.error("BookService - findById - Book is not exist");
            throw new RuntimeException("FindById - Book is not exist...");
        }
        return bookDTO;
    }

    @Override
    public void delete(Long id) {
        if (bookRepJdbc.delete(id)) {
            log.debug("Start BookService - deleteBookById: {}", id);
        } else {
            log.error("BookService - deleteBookById false: {}", id);
            throw new RuntimeException("DeleteBook - Book is not exist...");
        }
    }

    @Override
    public Book create(BookDto bookDTO) {
        log.debug("Start BookService - createBook {}", bookDTO);
        Book book = mapper.toBook(bookDTO);
        if (book == null) {
            log.error("BookService - create false:");
            throw new RuntimeException("CreateBook false...");
        }
        return bookRepJdbc.create(book);
    }

    @Override
    public Book update(BookDto bookDTO) {
        log.debug("Start BookService - updateBookById {}", bookDTO);
        Book book = mapper.toBook(bookDTO);
        if (book == null) {
            log.error("BookService - update false:");
            throw new RuntimeException("UpdateBook false...");
        }
        return bookRepJdbc.update(book);
    }

    @Override
    public Long countAll() {
        log.debug("Start BookService - countAllBooks");
        return bookRepJdbc.countAll();
    }
}
