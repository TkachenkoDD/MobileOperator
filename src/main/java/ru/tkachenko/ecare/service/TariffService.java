package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> showAll();

    Tariff showId(int id);

    void save(Tariff tariff);

    void update(Tariff tariff);

    void delete(Tariff tariff);
}
