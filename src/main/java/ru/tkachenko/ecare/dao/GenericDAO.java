package ru.tkachenko.ecare.dao;

import java.util.List;

public interface GenericDAO<T> {

    List<T> showAll();

    T showById(int id);

    void save(T t);

    void update(T t);

    void delete(T t, int id);
}
