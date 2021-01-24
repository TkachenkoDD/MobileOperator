package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

public interface TariffService {

    List<TariffDTO> showAll();

    TariffDTO showById(int id);

    void save(TariffDTO tariffDTO);

    void update(TariffDTO tariffDTO, List<Integer> optionList);

    void delete(TariffDTO tariffDTO, int id);

    Tariff toEntity(TariffDTO tariffDTO);
}
