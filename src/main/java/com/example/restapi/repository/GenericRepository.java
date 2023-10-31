package com.example.restapi.repository;

import java.util.List;

public interface GenericRepository<T, Integer> {
    T save(T target);

    T update(T target, Integer id);

    T getId(Integer id);

    List<T> getAll();

    boolean deleteById(Integer id);

}
