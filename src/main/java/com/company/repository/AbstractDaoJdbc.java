package com.company.repository;

import java.util.List;

public interface AbstractDaoJdbc<T> {
    T findById(Long id);

    List<T> findAll();

    T create(T entity);

    T update(T entity);

    boolean delete(Long id);
}
