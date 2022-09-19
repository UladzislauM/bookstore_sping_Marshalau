package com.company.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "name_author")
    private String nameAuthor;

    @Column(name = "date_release_book")
    private LocalDate dateReleaseBook;

    @Column(name = "cover")
    @Enumerated(EnumType.STRING)
    private CoverBook coverBook;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "book")
    private List<OrderItem> ordersItems;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(nameAuthor, book.nameAuthor) && Objects.equals(dateReleaseBook, book.dateReleaseBook) && coverBook == book.coverBook && Objects.equals(price, book.price) && Objects.equals(isbn, book.isbn) && Objects.equals(deleted, book.deleted) && Objects.equals(ordersItems, book.ordersItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, nameAuthor, dateReleaseBook, coverBook, price, isbn, deleted, ordersItems);
    }
}
