package ru.tkachenko.ecare.service;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.ClientDAO;
import ru.tkachenko.ecare.dao.ContractDAO;
import ru.tkachenko.ecare.dao.OptionDAO;
import ru.tkachenko.ecare.dao.TariffDAO;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Client;
import ru.tkachenko.ecare.models.Contract;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractDAO contractDAO;
    private final ClientDAO clientDAO;
    private final TariffDAO tariffDAO;
    private final OptionDAO optionDAO;
    private final ModelMapper modelMapper;

    private final Logger logger = Logger.getLogger(ContractServiceImpl.class);

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO, ClientDAO clientDAO, OptionDAO optionDAO,
                               TariffDAO tariffDAO, ModelMapper modelMapper) {
        this.contractDAO = contractDAO;
        this.clientDAO = clientDAO;
        this.tariffDAO = tariffDAO;
        this.optionDAO = optionDAO;
        this.modelMapper = modelMapper;
    }

    Contract contract = new Contract();

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContractDTO> showAll() {
        List<ContractDTO> contractDTOList = modelMapper.map(contractDAO.showAll(), new TypeToken<List<ContractDTO>>() {
        }.getType());
        contractDTOList.sort(Comparator.comparing(ContractDTO::getNumber));
        return contractDTOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ContractDTO showById(int id) {
        Contract contract = contractDAO.showById(id);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);
        contractDTO.setClientDTO(modelMapper.map(contract.getClient(), ClientDTO.class));
        Set<OptionDTO> optionDTOSet = modelMapper.map(contract.getOptionSet(), new TypeToken<Set<OptionDTO>>() {
        }.getType());
        contractDTO.setOptionDTOSet(optionDTOSet);
        if (contract.getTariff() != null) {
            contractDTO.setTariffDTO(modelMapper.map(contract.getTariff(), TariffDTO.class));
        }
        return contractDTO;
    }

    /**
     * {@inheritDoc}
     * @param number
     */
    @Override
    @Transactional(readOnly = true)
    public ClientDTO showClientByNumber(String number) {
        Contract contract = (Contract) contractDAO.showClientByNumber(number);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);
        contractDTO.setClientDTO(modelMapper.map(contract.getClient(), ClientDTO.class));
        Set<ContractDTO> contractDTOSet = modelMapper.map(contract.getClient().getContractSet(), new TypeToken<Set<ContractDTO>>() {
        }.getType());
        contractDTO.getClientDTO().setContractSetDTO(contractDTOSet);
        logger.info("Searching client with number " + number);
        return contractDTO.getClientDTO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(ContractDTO contractDTO) {
        contract = toEntity(contractDTO);
        contractDAO.save(contract);
        logger.info("Contract created");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(ContractDTO contractDTO) {
        contract = toEntity(contractDTO);
        contractDAO.update(contract);
        logger.info("Contract updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(ContractDTO contractDTO, int id) {
        contract = toEntity(contractDTO);
        contractDAO.delete(contract, id);
        logger.info("Contract deleted");
    }

    /**
     * {@inheritDoc}
     */
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
        logger.info("Contract blocked/unblocked by " + currentName + ". Set status: " + status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contract toEntity(ContractDTO contractDTO) {
        return modelMapper.map(contractDTO, Contract.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Set<OptionDTO> showAvailableOptions(ContractDTO contractDTO, HttpSession session) {
        Set<OptionDTO> availableOptionSet = contractDTO.getTariffDTO().getOptionAvailableSet();
        if (!contractDTO.getOptionDTOSet().isEmpty()) {
            for (OptionDTO optionDTO1 : contractDTO.getOptionDTOSet()) {
                availableOptionSet.removeIf(optionDTO -> optionDTO.getCategory().equals(optionDTO1.getCategory()));
            }
        }
        if (session.getAttribute("contract") != null) {
            ContractDTO contractDTO1 = (ContractDTO) session.getAttribute("contract");
            for (OptionDTO optionDTO1 : contractDTO1.getOptionDTOSet()) {
                availableOptionSet.removeIf(optionDTO -> optionDTO1.getCategory().equals(optionDTO.getCategory()));
            }
        }
        return availableOptionSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addOptionToCart(int contractId, int optionId, HttpSession session) {
        ContractDTO contractDTO;
        if (session.getAttribute("contract") == null) {
            contractDTO = showById(contractId);
            Set<OptionDTO> optionDTOSet = new TreeSet<>();
            optionDTOSet.add(modelMapper.map(optionDAO.showById(optionId), OptionDTO.class));
            contractDTO.setOptionDTOSet(optionDTOSet);
        } else {
            contractDTO = (ContractDTO) session.getAttribute("contract");
            contractDTO.getOptionDTOSet().add(modelMapper.map(optionDAO.showById(optionId), OptionDTO.class));
        }
        session.setAttribute("contract", contractDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteOptionFromCart(int optionId, HttpSession session) {
        ContractDTO contractDTO = (ContractDTO) session.getAttribute("contract");
        contractDTO.getOptionDTOSet().remove(modelMapper.map(optionDAO.showById(optionId), OptionDTO.class));
        if (contractDTO.getOptionDTOSet().isEmpty()) {
            session.removeAttribute("contract");
        } else {
            session.setAttribute("contract", contractDTO);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void confirmCartContract(ContractDTO contractDTO, HttpSession session) {
        ContractDTO contractDTO1 = showById(contractDTO.getId());
        contractDTO = (ContractDTO) session.getAttribute("contract");
        for (OptionDTO optionDTO : contractDTO.getOptionDTOSet()) {
            contractDTO1.getOptionDTOSet().add(optionDTO);
        }
        update(contractDTO1);
        logger.info("Confirmed changes from cart");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void changeTariffOnContract(int contractId, int tariffId) {
        ContractDTO contractDTO = showById(contractId);
        contractDTO.setTariffDTO(modelMapper.map(tariffDAO.showById(tariffId), TariffDTO.class));
        contractDTO.getOptionDTOSet().clear();
        update(contractDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteOptionFromContract(int contractId, int optionId) {
        ContractDTO contractDTO = showById(contractId);
        contractDTO.getOptionDTOSet().remove(modelMapper.map(optionDAO.showById(optionId), OptionDTO.class));
        update(contractDTO);
    }
}