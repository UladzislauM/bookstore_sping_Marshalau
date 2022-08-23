package com.company.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
@Component
public class Book {
    private Long id;
    private String title;
    private String nameAuthor;
    private LocalDate dateReleaseBook;
    private CoverBook coverBook;
    private BigDecimal price;
    private String isbn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public LocalDate getDateReleaseBook() {
        return dateReleaseBook;
    }

    public void setDateReleaseBook(LocalDate dateReleaseBook) {
        this.dateReleaseBook = dateReleaseBook;
    }

    public CoverBook getCover() {
        return coverBook;
    }

    public void setCover(CoverBook coverBook) {
        this.coverBook = coverBook;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(nameAuthor, book.nameAuthor) && Objects.equals(dateReleaseBook, book.dateReleaseBook) && Objects.equals(coverBook, book.coverBook) && Objects.equals(price, book.price) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, nameAuthor, dateReleaseBook, coverBook, price, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", nameAuthor='" + nameAuthor + '\'' +
                ", bookReleaseDate=" + dateReleaseBook +
                ", status='" + coverBook + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
