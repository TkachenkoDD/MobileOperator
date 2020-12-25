package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

public interface GenericService<T> {

    List<T> showAll();

    T showById(int id);

    void save(T t);

    void update(T t);

    void delete(T t);
}
