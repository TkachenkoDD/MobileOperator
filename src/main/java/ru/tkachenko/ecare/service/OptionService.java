package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.models.Option;

import java.util.List;

public interface OptionService {

    List<Option> showAll();

    Option showById(int id);

    void save(Option option);

    void update(Option option);

    void delete(Option option);
}
