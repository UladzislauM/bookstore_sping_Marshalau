package com.company.data.repository.impl;

import com.company.data.dao.impl.BookDaoJdbcImpl;
import com.company.data.dto.BookDaoDto;
import com.company.service.entity.Book;
import com.company.data.repository.BookRepJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("bookRep")
@RequiredArgsConstructor
public class BookRepJdbcImpl implements BookRepJdbc {
    private final BookDaoJdbcImpl bookDaoJdbc;
    private final ObjectMapperDao mapper;

    @Override
    public Book findById(Long id) {
        BookDaoDto bookDTO = bookDaoJdbc.findById(id);
        if (bookDTO != null) {
            return mapper.toBook(bookDTO);
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<BookDaoDto> booksDTO = bookDaoJdbc.findAll();
        List<Book> books = new ArrayList<>();
        if (booksDTO != null) {
            booksDTO.stream().forEach(bookDTO -> books.add(mapper.toBook(bookDTO)));
            return books;
        } else {
            return null;
        }
    }

    @Override
    public Book create(Book book) {
        if (book != null) {
            return mapper.toBook(bookDaoJdbc.create(mapper.toBookDaoDTO(book)));
        }
        return null;
    }

    @Override
    public Book update(Book book) {
        if (book != null) {
            return mapper.toBook(bookDaoJdbc.update(mapper.toBookDaoDTO(book)));
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return bookDaoJdbc.delete(id);
    }

    @Override
    public Long countAll() {
        return bookDaoJdbc.countAll();
    }
}
