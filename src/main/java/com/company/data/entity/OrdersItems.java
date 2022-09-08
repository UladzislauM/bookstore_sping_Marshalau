package com.company.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "orders_items")
public class OrdersItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersItems that = (OrdersItems) o;
        return Objects.equals(id, that.id) && Objects.equals(orders, that.orders) && Objects.equals(book, that.book) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orders, book, quantity, price);
    }

    @Override
    public String toString() {
        return "OrdersItem{" +
                "id=" + id +
                ", orders=" + orders.getId() +
                ", book=" + book.getId() +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
