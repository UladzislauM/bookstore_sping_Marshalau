package com.company.data.repository;

import com.company.data.entity.OrdersItems;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AbstractRep<T> {
    T findById(Long id);

    List<T> findAll();

    T create(T entity);

    T update(T entity);

    boolean delete(Long id);
}
