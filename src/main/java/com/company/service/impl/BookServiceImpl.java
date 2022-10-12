package com.company.service.impl;

import com.company.data.entity.Book;
import com.company.data.repository.OrderItemRep;
import com.company.service.dto.BookDto;
import com.company.data.repository.BookRep;
import com.company.service.BookService;
import com.company.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service("bookService")
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private final BookRep bookRepJdbc;
    private final ObjectMapperSC mapper;
    private final OrderItemRep orderItemRep;

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
            return books.stream().map(mapper::toBookDTO).toList();
        }
    }

    @Override
    public List<BookDto> findAllAuthors() {
        log.debug("Start BookService - findAllAuthors");
        List<Book> books = bookRepJdbc.findAll();
        if (books == null) {
            log.error("BookService - findAllAuthors - Books is not exist");
            throw new RuntimeException("findAllAuthors - Books is not exist...");
        } else {
            return books.stream().map(mapper::toBookDTO)
                    .sorted(Comparator.comparing(BookDto::getNameAuthor))
                    .collect(Collectors.toCollection(
                    () -> new TreeSet<BookDto>((p1, p2) -> p1.getNameAuthor().compareTo(p2.getNameAuthor()))))
                    .stream().toList();
        }
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Start BookService - getBookById {}", id);
        Book book = bookRepJdbc.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("User with id: " + id + " wasn't found");
        });
        BookDto bookDTO = mapper.toBookDTO(book);
        if (bookDTO == null) {
            log.error("BookService - findById - Book is not exist");
            throw new RuntimeException("FindById - Book is not exist...");
        }
        orderItemRep.findByOrdersId(id);
        return bookDTO;
    }

    @Override
    public List<BookDto> findByAuthor(String author) {
        log.debug("Start BookService - findByAuthor {}", author);
        List<Book> books= bookRepJdbc.findByAuthor(author);
        if (books == null) {
            log.error("BookService - findByAuthor - Book is not exist");
            throw new RuntimeException("findByAuthor - Book is not exist...");
        }
        return books.stream().map(mapper::toBookDTO).toList();
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
    public BookDto create(BookDto bookDTO) {
        log.debug("Start BookService - createBook {}", bookDTO);
        Book book = mapper.toBook(bookDTO);
        if (book == null) {
            log.error("BookService - create false:");
            throw new RuntimeException("CreateBook false...");
        }
        return mapper.toBookDTO(bookRepJdbc.create(book));
    }

    @Override
    public BookDto update(BookDto bookDTO) {
        log.debug("Start BookService - updateBookById {}", bookDTO);
        Book book = mapper.toBook(bookDTO);
        if (book == null) {
            log.error("BookService - update false:");
            throw new RuntimeException("UpdateBook false...");
        }
        return mapper.toBookDTO(bookRepJdbc.update(book));
    }

    @Override
    public Long countAll() {
        log.debug("Start BookService - countAllBooks");
        return bookRepJdbc.countAll();
    }

    @Override
    public Long countAllAuthors() {
        log.debug("Start BookService - countAllAuthors");
        return bookRepJdbc.countAllAuthors();
    }
}
