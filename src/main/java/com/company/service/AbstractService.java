package com.company.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbstractService<T, D> {
    List<D> findAll();

    D findById(Long id);

    void delete(Long id);

    T create(D d);

    T update(D d);
}

