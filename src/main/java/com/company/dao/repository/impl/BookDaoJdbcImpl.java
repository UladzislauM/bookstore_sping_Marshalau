package com.company.dao.repository.impl;

import com.company.dao.entity.Book;
import com.company.dao.entity.StatusBook;
import com.company.dao.repository.BookDaoJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bookDao")
public class BookDaoJdbcImpl implements BookDaoJdbc {
    private static final String GET_ALL = "SELECT books.id, books.title, books.name_author, books.date_release_book, books.price," +
            " books.isbn, status.status_name FROM books JOIN status ON books.status_id = status.Id;";
    private static final String GET_BY_ID = "SELECT books.id, books.title, books.name_author, books.date_release_book, books.price, " +
            "books.isbn, status.status_name FROM books JOIN status ON books.status_id = status.Id WHERE books.id =:id";
    private static final String DELETE_BY_ID = "DELETE FROM books WHERE id = :id";
    private static final String ADD_BOOK = "INSERT INTO books (title, name_author, date_release_book," +
            " status_id, price, isbn) VALUES (:title, :name_author, :date_release_book, " +
            "(SELECT id FROM status WHERE status_name = :status_name), :price, :isbn)";
    private static final String UPDATE_BY_ID = "UPDATE books SET title = :title, name_author = :name_author, date_release_book = :date_release_book," +
            "status_id = (SELECT id FROM status WHERE status_name = :status_name), price = :price, isbn = :isbn where id = :id;";
    private static final String COUNT_BOOKS = "SELECT count(*) AS total FROM books";

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public BookDaoJdbcImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public Book findById(Long id) {
        try {
            return namedJdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), this::processRow);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<Book> findAll() {
        return namedJdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public Book create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> map = new HashMap<>();
        extractedBook(book, map);
        namedJdbcTemplate.update(ADD_BOOK, new MapSqlParameterSource().addValues(map),
                keyHolder, new String[]{"id"});
        Number number = keyHolder.getKey();
        if (number != null) {
            Long id = number.longValue();
            return findById(id);
        }
        return null;
    }

    @Override
    public Book update(Book book) {
        Map<String, Object> map = new HashMap<>();
        extractedBook(book, map);
        namedJdbcTemplate.update(UPDATE_BY_ID, map);
        return findById(book.getId());
    }

    @Override
    public boolean delete(Long id) {
        int rowsUpdated = namedJdbcTemplate.update(DELETE_BY_ID,
                new MapSqlParameterSource("id", id));
        return rowsUpdated == 1;
    }

    private void extractedBook(Book book, Map<String, Object> map) {
        map.put("title", book.getTitle());
        map.put("name_author", book.getNameAuthor());
        map.put("date_release_book", Date.valueOf(book.getDateReleaseBook()));
        map.put("price", book.getPrice());
        map.put("isbn", book.getIsbn());
        map.put("status_name", String.valueOf(book.getStatus()));
        map.put("id", book.getId());
    }

    public Book processRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setNameAuthor(resultSet.getString("name_author"));
        book.setDateReleaseBook(resultSet.getTimestamp("date_release_book").toLocalDateTime().toLocalDate());
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setStatus(StatusBook.valueOf(resultSet.getString("status_name")));
        return book;
    }

}
