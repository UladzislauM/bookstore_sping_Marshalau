package com.company.data.dao.impl;

import com.company.data.dao.BookDaoJdbc;
import com.company.data.dataDTO.BookDaoDTO;
import com.company.entity.Book;
import com.company.entity.CoverBook;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
@RequiredArgsConstructor
public class BookDaoJdbcImpl implements BookDaoJdbc {
    private static final String GET_ALL = """
            SELECT books.id, books.title, books.name_author, books.date_release_book, books.price, books.isbn, cover.cover_name 
            FROM books 
            JOIN cover 
            ON books.cover_id = cover.id
            WHERE deleted = FALSE
            """;
    private static final String GET_BY_ID = """
            SELECT books.id, books.title, books.name_author, books.date_release_book, books.price, books.isbn, cover.cover_name 
            FROM books 
            JOIN cover 
            ON books.cover_id = cover.id 
            WHERE books.id =:id AND deleted = FALSE 
            """;
    private static final String ADD_BOOK = """
            INSERT INTO books (title, name_author, date_release_book, cover_id, price, isbn) 
            VALUES (:title, :name_author, :date_release_book,(SELECT id FROM cover WHERE cover_name = :cover_name), :price, :isbn)
            """;
    private static final String UPDATE_BY_ID = """
            UPDATE books 
            SET title = :title, name_author = :name_author, date_release_book = :date_release_book, cover_id = (SELECT id FROM cover WHERE cover_name = :cover_name), price = :price, isbn = :isbn 
            WHERE id = :id AND deleted = FALSE
            """;
    private static final String DELETE_BY_ID = """
            UPDATE books 
            SET deleted = TRUE 
            WHERE id = :id AND deleted = FALSE
            """;
    private static final String COUNT_BOOKS = """
            SELECT count(*) 
            AS total 
            FROM books
            """;

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public BookDaoDTO findById(Long id) {
        try {
            return namedJdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), this::processRow);
        } catch (EmptyResultDataAccessException ignored) {
            return null;
        }
    }

    @Override
    public List<BookDaoDTO> findAll() {
        return namedJdbcTemplate.query(GET_ALL, this::processRow);
    }

    @Override
    public BookDaoDTO create(BookDaoDTO book) {
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
    public BookDaoDTO update(BookDaoDTO book) {
        Map<String, Object> map = new HashMap<>();
        extractedBook(book, map);
        namedJdbcTemplate.update(UPDATE_BY_ID, map);
        return findById(book.getId());
    }

    @Override
    public boolean delete(Long id) {
        int rowsUpdated = namedJdbcTemplate.update(DELETE_BY_ID, new MapSqlParameterSource("id", id));
        return rowsUpdated == 1;
    }

    @Override
    public Long countAll() {
        return namedJdbcTemplate.queryForObject(COUNT_BOOKS, new MapSqlParameterSource(), Long.class);
    }

    private void extractedBook(BookDaoDTO book, Map<String, Object> map) {
        map.put("title", book.getTitle());
        map.put("name_author", book.getNameAuthor());
        map.put("date_release_book", Date.valueOf(book.getDateReleaseBook()));
        map.put("price", book.getPrice());
        map.put("isbn", book.getIsbn());
        map.put("cover_name", String.valueOf(book.getCoverBookDaoDTO()));
        map.put("id", book.getId());
        map.put("deleted", book.getDeleted());
    }

    public BookDaoDTO processRow(ResultSet resultSet, int rowNum) throws SQLException {
        BookDaoDTO book = new BookDaoDTO();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setNameAuthor(resultSet.getString("name_author"));
        book.setDateReleaseBook(resultSet.getTimestamp("date_release_book").toLocalDateTime().toLocalDate());
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setCoverBookDaoDTO(CoverBook.valueOf(resultSet.getString("cover_name")));
        return book;
    }
}
