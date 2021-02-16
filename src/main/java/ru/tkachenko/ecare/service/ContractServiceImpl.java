package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.ClientDAO;
import ru.tkachenko.ecare.dao.ContractDAO;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Client;
import ru.tkachenko.ecare.models.Contract;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractDAO contractDAO;
   private final ClientDAO clientDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO, ClientDAO clientDAO,
                               ModelMapper modelMapper) {
        this.contractDAO = contractDAO;
        this.clientDAO = clientDAO;
        this.modelMapper = modelMapper;
    }

    Contract contract = new Contract();

    @Override
    @Transactional(readOnly = true)
    public List<ContractDTO> showAll() {
        List<ContractDTO> contractDTOList = modelMapper.map(contractDAO.showAll(), new TypeToken<List<ContractDTO>>() {
        }.getType());
        contractDTOList.sort(Comparator.comparing(ContractDTO::getNumber));
        return contractDTOList;
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
    public void update(ContractDTO contractDTO) {
        contract = toEntity(contractDTO);
        contractDAO.update(contract);
    }

    @Override
    @Transactional
    public void delete(ContractDTO contractDTO, int id) {
        contract = toEntity(contractDTO);
        contractDAO.delete(contract, id);
    }

    @Override
    @Transactional
    public void contractBlock(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Contract contract = contractDAO.showById(id);
        Client client = (Client) clientDAO.showByName(currentName);
        int status = contract.getStatus();
        String role = client.getRole().name();
        if (status == 0) {
            if (role.equals("USER")) {
                contract.setStatus(1);
            } else contract.setStatus(2);
        }
        if ((role.equals("USER")) && (status == 1)) {
            contract.setStatus(0);
        }
        if ((role.equals("ADMIN")) && ((status > 0))) {
            contract.setStatus(0);
        }
        contractDAO.update(contract);
    }

    @Override
    public Contract toEntity(ContractDTO contractDTO) {
        return modelMapper.map(contractDTO, Contract.class);
    }
}
