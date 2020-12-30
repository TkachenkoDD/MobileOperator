package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.OptionDAO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.models.Option;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionDAO optionDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public OptionServiceImpl(OptionDAO optionDAO, ModelMapper modelMapper) {
        this.optionDAO = optionDAO;
        this.modelMapper = modelMapper;
    }

    Option option = new Option();

    @Override
    @Transactional(readOnly = true)
    public List<OptionDTO> showAll() {
        return modelMapper.map(optionDAO.showAll(), new TypeToken<List<OptionDTO>>() {}.getType());
    }

    @Override
    @Transactional(readOnly = true)
    public OptionDTO showById(int id) {
        return modelMapper.map(optionDAO.showById(id), OptionDTO.class);
    }

    @Override
    @Transactional
    public void save(OptionDTO optionDTO) {
        option = toEntity(optionDTO);
        optionDAO.save(option);

    }

    @Override
    @Transactional
    public void update(OptionDTO optionDTO) {
        option = toEntity(optionDTO);
        optionDAO.update(option);

    }

    @Override
    @Transactional
    public void delete(OptionDTO optionDTO, int id) {
        option = toEntity(optionDTO);
        optionDAO.delete(option, id);
    }

    @Override
    public Option toEntity(OptionDTO optionDTO) {
        return modelMapper.map(optionDTO, Option.class);
    }
}