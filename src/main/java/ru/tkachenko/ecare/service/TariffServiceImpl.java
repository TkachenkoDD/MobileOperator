package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.OptionDAO;
import ru.tkachenko.ecare.dao.TariffDAO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Option;
import ru.tkachenko.ecare.models.Tariff;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TariffServiceImpl implements TariffService {

    private final TariffDAO tariffDAO;
    private final OptionDAO optionDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public TariffServiceImpl(TariffDAO tariffDAO, OptionDAO optionDAO, ModelMapper modelMapper) {
        this.tariffDAO = tariffDAO;
        this.optionDAO = optionDAO;
        this.modelMapper = modelMapper;
    }

    Tariff tariff = new Tariff();

    @Override
    @Transactional(readOnly = true)
    public List<TariffDTO> showAll() {
        List<TariffDTO> tariffDTOList = modelMapper.map(tariffDAO.showAll(), new TypeToken<List<TariffDTO>>() {}.getType());
        tariffDTOList.sort(Comparator.comparing(TariffDTO::getTariffName));
        return tariffDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public TariffDTO showById(int id) {
        Tariff tariff = tariffDAO.showById(id);
        TariffDTO tariffDTO = modelMapper.map(tariff, TariffDTO.class);
        Set<ContractDTO> contractDTOSet = modelMapper.map(tariff.getContractSet(), new TypeToken<Set<ContractDTO>>() {}.getType());
        Set<OptionDTO> optionDTOSet = modelMapper.map(tariff.getOptionAvailableSet(), new TypeToken<Set<OptionDTO>>() {}.getType());
        tariffDTO.setContractSet(contractDTOSet);
        tariffDTO.setOptionAvailableSet(optionDTOSet);
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
    public void update(TariffDTO tariffDTO, List<Integer> optionList) {
        tariff = toEntity(tariffDTO);
        Set<Option> optionSet = new HashSet<>();
        for(Integer x: optionList){
            if (x != null)
            optionSet.add(optionDAO.showById(x));
        }
        if (!optionSet.isEmpty())
        tariff.setOptionAvailableSet(optionSet);
        tariffDAO.update(tariff);
    }

    @Override
    @Transactional
    public void delete(TariffDTO tariffDTO, int id) {
        tariff = toEntity(tariffDTO);
        tariffDAO.delete(tariff, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TariffDTO> loadTariffs() {
        List<TariffDTO> tariffDTOList = modelMapper.map(tariffDAO.showAll(), new TypeToken<List<TariffDTO>>() {}.getType());
        for (TariffDTO tariffDTO: tariffDTOList){
            tariffDTO.setContractSet(null);
            for (OptionDTO optionDTO: tariffDTO.getOptionAvailableSet()){
                optionDTO.setTariffSet(null);
                optionDTO.setContractDTOSet(null);
            }
        }
        tariffDTOList.sort(Comparator.comparing(TariffDTO::getTariffName));
        return tariffDTOList;
    }

    @Override
    public Tariff toEntity(TariffDTO tariffDTO) {
        return modelMapper.map(tariffDTO, Tariff.class);
    }
}
