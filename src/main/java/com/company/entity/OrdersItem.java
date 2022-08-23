package com.company.entity;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrdersItem {
    private Long id;
    private Orders orders;
    private Book book;
    private Integer quantity;
    private BigDecimal price;

    @Override
    public String toString() {
        return "OrdersItem{" +
                "id=" + id +
                ", orders=" + orders.getId() +
                ", book=" + book +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
