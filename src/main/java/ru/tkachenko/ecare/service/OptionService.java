package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.models.Option;

import java.util.List;

public interface OptionService {

    List<OptionDTO> showAll();

    OptionDTO showById(int id);

    void save(OptionDTO optionDTO);

    void update(OptionDTO optionDTO);

    void delete(OptionDTO optionDTO, int id);

    Option toEntity(OptionDTO optionDTO);
}
