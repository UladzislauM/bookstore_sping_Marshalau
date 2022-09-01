package com.company.service.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class OrdersItems {
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
