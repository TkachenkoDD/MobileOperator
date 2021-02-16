package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.OptionDAO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Option;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<OptionDTO> optionDTOList = modelMapper.map(optionDAO.showAll(), new TypeToken<List<OptionDTO>>() {
        }.getType());
        for (OptionDTO optionDTO : optionDTOList) {
            Option option = optionDAO.showById(optionDTO.getId());
            Set<TariffDTO> tariffDTOSet = modelMapper.map(option.getTariffSet(), new TypeToken<Set<TariffDTO>>() {}.getType());
            Set<ContractDTO> contractDTOSet = modelMapper.map(option.getContractSet(), new TypeToken<Set<ContractDTO>>() {
            }.getType());
            optionDTO.setTariffSet(tariffDTOSet);
            optionDTO.setContractDTOSet(contractDTOSet);
        }
        optionDTOList = optionDTOList.stream().sorted(Comparator.comparing(OptionDTO::getCategory)
                .thenComparing(OptionDTO::getOptionName)).collect(Collectors.toList());
        return optionDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public OptionDTO showById(int id) {
        Option option = optionDAO.showById(id);
        OptionDTO optionDTO = modelMapper.map(option, OptionDTO.class);
        Set<TariffDTO> tariffDTOSet = modelMapper.map(option.getTariffSet(), new TypeToken<Set<TariffDTO>>() {
        }.getType());
        optionDTO.setTariffSet(tariffDTOSet);
        return optionDTO;
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