package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.ContractDAO;
import ru.tkachenko.ecare.dao.OptionDAO;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Contract;
import ru.tkachenko.ecare.models.Option;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractDAO contractDAO;
    private final OptionDAO optionDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO, OptionDAO optionDAO, ModelMapper modelMapper) {
        this.contractDAO = contractDAO;
        this.optionDAO = optionDAO;
        this.modelMapper = modelMapper;
    }

    Contract contract = new Contract();

    @Override
    @Transactional(readOnly = true)
    public List<ContractDTO> showAll() {
        return modelMapper.map(contractDAO.showAll(), new TypeToken<List<ContractDTO>>() {
        }.getType());
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDTO showById(int id) {
        Contract contract = contractDAO.showById(id);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);
        contractDTO.setClientDTO(modelMapper.map(contract.getClient(), ClientDTO.class));
        Set<OptionDTO> optionDTOSet = modelMapper.map(contract.getOptionSet(), new TypeToken<Set<OptionDTO>>() {}.getType());
        contractDTO.setOptionDTOSet(optionDTOSet);
        if (contract.getTariff() != null) {
            contractDTO.setTariffDTO(modelMapper.map(contract.getTariff(), TariffDTO.class));
        }
        return contractDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO showClientByNumber(int number) {
        Contract contract = (Contract) contractDAO.showClientByNumber(number);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);
        contractDTO.setClientDTO(modelMapper.map(contract.getClient(), ClientDTO.class));
        Set<ContractDTO> contractDTOSet = modelMapper.map(contract.getClient().getContractSet(), new TypeToken<Set<ContractDTO>>() {
        }.getType());
        contractDTO.getClientDTO().setContractSetDTO(contractDTOSet);
        return contractDTO.getClientDTO();
    }

    @Override
    @Transactional
    public void save(ContractDTO contractDTO) {
        contract = toEntity(contractDTO);
        contractDAO.save(contract);
    }

    @Override
    @Transactional
    public void update(ContractDTO contractDTO, List<Integer> optionList) {
        contract = toEntity(contractDTO);
        Set<Option> optionSet = new HashSet<>();
        for (Integer x : optionList) {
            if (x != null)
                optionSet.add(optionDAO.showById(x));
        }
        if (!optionSet.isEmpty())
        contract.setOptionSet(optionSet);
        contractDAO.update(contract);
    }

    @Override
    @Transactional
    public void delete(ContractDTO contractDTO, int id) {
        contract = toEntity(contractDTO);
        contractDAO.delete(contract, id);
    }

    @Override
    public Contract toEntity(ContractDTO contractDTO) {
        return modelMapper.map(contractDTO, Contract.class);
    }
}
