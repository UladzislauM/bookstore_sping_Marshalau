package com.company.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "books")
public class Books {
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
    private List<OrdersItems> ordersItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(id, books.id) && Objects.equals(title, books.title) && Objects.equals(nameAuthor, books.nameAuthor) && Objects.equals(dateReleaseBook, books.dateReleaseBook) && coverBook == books.coverBook && Objects.equals(price, books.price) && Objects.equals(isbn, books.isbn) && Objects.equals(deleted, books.deleted) && Objects.equals(ordersItems, books.ordersItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, nameAuthor, dateReleaseBook, coverBook, price, isbn, deleted, ordersItems);
    }
}
