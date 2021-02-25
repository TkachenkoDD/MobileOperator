package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

/**
 * created by Dmitrii Tkachenko
 */
public interface TariffService {
    /**
     * Find list of tariffs from data base
     */
    List<TariffDTO> showAll();

    /**
     * Find tariff by Id from data base
     *
     * @param id - tariff's Id
     */
    TariffDTO showById(int id);

    /**
     * save new tariff in data base
     */
    void save(TariffDTO tariffDTO);

    /**
     * update tariff
     */
    void update(TariffDTO tariffDTO, List<Integer> optionList);

    /**
     * Delete tariff from data base
     *
     * @param tariffDTO - object for deleting
     * @param id        - tariff's Id
     */
    void delete(TariffDTO tariffDTO, int id);

    /**
     * Method for converting DTO to entity
     *
     * @param tariffDTO - DTO
     * @return tariff
     */
    Tariff toEntity(TariffDTO tariffDTO);

    /**
     * Prepare list of tariffs for transmitting to second application
     *
     * @return - list of tariffs
     */
    List<TariffDTO> loadTariffs();
}
