package com.company.repository;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AbstractDaoJdbc<T> {
    T findById(Long id);

    List<T> findAll();

    T create(T entity);

    T update(T entity);

    boolean delete(Long id);

    Long countAll();
}
