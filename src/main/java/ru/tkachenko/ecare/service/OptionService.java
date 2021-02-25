package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.models.Option;

import java.util.List;

/**
 * created by Dmitrii Tkachenko
 */
public interface OptionService {
    /**
     * Find list of options from data base
     */
    List<OptionDTO> showAll();

    /**
     * Find option by Id from data base
     *
     * @param id - option's Id
     */
    OptionDTO showById(int id);

    /**
     * save new option in data base
     */
    void save(OptionDTO optionDTO);

    /**
     * update option
     */
    void update(OptionDTO optionDTO);

    /**
     * Delete option from data base
     *
     * @param optionDTO - object for deleting
     * @param id        - option's Id
     */
    void delete(OptionDTO optionDTO, int id);

    /**
     * Method for converting DTO to entity
     *
     * @param optionDTO - DTO
     * @return option
     */
    Option toEntity(OptionDTO optionDTO);
}
