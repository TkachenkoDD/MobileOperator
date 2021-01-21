package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.TariffDAO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Tariff;

import java.util.List;
import java.util.Set;

@Service
public class TariffServiceImpl implements TariffService {

    private final TariffDAO tariffDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public TariffServiceImpl(TariffDAO tariffDAO, ModelMapper modelMapper) {
        this.tariffDAO = tariffDAO;
        this.modelMapper = modelMapper;
    }

    Tariff tariff = new Tariff();

    @Override
    @Transactional(readOnly = true)
    public List<TariffDTO> showAll() {
        return modelMapper.map(tariffDAO.showAll(), new TypeToken<List<TariffDTO>>() {}.getType());
    }

    @Override
    @Transactional(readOnly = true)
    public TariffDTO showById(int id) {
        Tariff tariff = tariffDAO.showById(id);
        TariffDTO tariffDTO = modelMapper.map(tariff, TariffDTO.class);
        Set<ContractDTO> contractDTOSet = modelMapper.map(tariff.getContractSet(), new TypeToken<Set<ContractDTO>>() {}.getType());
        Set<OptionDTO> optionDTOSet = modelMapper.map(tariff.getOptionSet(), new TypeToken<Set<OptionDTO>>() {}.getType());
        tariffDTO.setContractSet(contractDTOSet);
        tariffDTO.setOptionSet(optionDTOSet);
        return tariffDTO;
    }

    @Override
    @Transactional
    public void save(TariffDTO tariffDTO) {
        tariff = toEntity(tariffDTO);
        tariffDAO.save(tariff);
    }

    @Override
    @Transactional
    public void update(TariffDTO tariffDTO) {
        tariff = toEntity(tariffDTO);
        tariffDAO.update(tariff);
    }

    @Override
    @Transactional
    public void delete(TariffDTO tariffDTO, int id) {
        tariff = toEntity(tariffDTO);
        tariffDAO.delete(tariff, id);
    }

    @Override
    public Tariff toEntity(TariffDTO tariffDTO) {
        return modelMapper.map(tariffDTO, Tariff.class);
    }
}
