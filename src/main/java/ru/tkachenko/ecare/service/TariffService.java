package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

public interface TariffService {

    List<Tariff> showAll();

    Tariff showById(int id);

    void save(Tariff tariff);

    void update(Tariff tariff);

    void delete(Tariff tariff, int id);
}
