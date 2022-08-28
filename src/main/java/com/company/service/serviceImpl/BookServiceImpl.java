package com.company.service.serviceImpl;

import com.company.DTO.BookDTO;
import com.company.entity.Book;
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
    private final BookRepJdbc bookRepJdbc;
    private final ObjectMapperSC mapper;

    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);

    public void validate(Book book) {
        if (book.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            throw new RuntimeException("Price is not valid, ...");
        }
    }

    @Override
    public List<BookDTO> findAll() {
        List<Book> books = bookRepJdbc.findAll();
        log.debug("Start BookService - getAllBooks - {}", books.size());
        List<BookDTO> booksDTO = books.stream().map(book -> {
            return mapper.toBookDTO(book);
        }).toList();
        return booksDTO;
    }

    @Override
    public BookDTO findById(Long id) {
        log.debug("Start BookService - getBookById {}", id);
        BookDTO bookDTO = mapper.toBookDTO(bookRepJdbc.findById(id));
        return bookDTO;
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
    public Book create(BookDTO bookDTO) {
        log.debug("Start BookService - createBook {}", bookDTO);
        Book book = mapper.toBook(bookDTO);
        return bookRepJdbc.create(book);
    }

    @Override
    public Book update(BookDTO bookDTO) {
        log.debug("Start BookService - updateBookById {}", bookDTO);
        Book book = mapper.toBook(bookDTO);
        return bookRepJdbc.update(book);
    }

    @Override
    public Long countAll() {
        log.debug("Start BookService - countAllBooks");
        return bookRepJdbc.countAll();
    }
}
