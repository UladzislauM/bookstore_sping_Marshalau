package com.company.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "timestamp")
    private LocalDate timestamp;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusBook status;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<OrderItem> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(totalCost, order.totalCost) && Objects.equals(timestamp, order.timestamp) && status == order.status && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, totalCost, timestamp, status, items);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", totalCost=" + totalCost +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", items=" + items +
                '}';
    }
}
