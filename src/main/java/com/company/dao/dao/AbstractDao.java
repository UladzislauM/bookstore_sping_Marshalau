package com.company.dao.dao;

import java.util.List;

public interface AbstractDao<T, K> {
    T create(T entity);

    T findById(K id);

    List<T> findAll();

    K countAll();

    T update(T entity);

    boolean delete(K id);
}
