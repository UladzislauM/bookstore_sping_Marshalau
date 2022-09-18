package com.company.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User userC;

    @ManyToOne
    @JoinColumn(name = "books_id")
    private BooksInCart booksInCart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(userC, cart.userC) && Objects.equals(booksInCart, cart.booksInCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userC, booksInCart);
    }
}
