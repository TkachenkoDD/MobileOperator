package ru.tkachenko.ecare.service;

import java.util.List;

public interface GenericService<T> {

    List<T> showAll();

    T showById(int id);

    void save(T t);

    void update(T t);

    void delete(T t);
}
