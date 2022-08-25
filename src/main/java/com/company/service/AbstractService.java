package com.company.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbstractService<T> {
    List<T> findAll();

    T findById(Long id);

    void delete(Long id);

    T create(T t);

    T update(T t);
}

