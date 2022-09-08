package com.company.data.repository;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AbstractRep<T> {
    T findById(Long id);

    List<T> findAll();

    T update(T entity);

    boolean delete(Long id);
}
