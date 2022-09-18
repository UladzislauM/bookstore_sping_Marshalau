package com.company.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "books_in_cart")
public class BooksInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books books;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "booksInCart", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<Cart> cartList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksInCart that = (BooksInCart) o;
        return Objects.equals(id, that.id) && Objects.equals(books, that.books) && Objects.equals(quantity, that.quantity) && Objects.equals(cartList, that.cartList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, books, quantity, cartList);
    }
}
