package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.models.Contract;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public interface ContractService {

    List<ContractDTO> showAll();

    ContractDTO showById(int id);

    void save(ContractDTO contractDTO);

    void update(ContractDTO contractDTO);

    void delete(ContractDTO contractDTO, int id);

    ClientDTO showClientByNumber(int number);

    void contractBlock(int id);

    Contract toEntity(ContractDTO contractDTO);

    void confirmCartContract(ContractDTO contractDTO, HttpSession session);

    void deleteOptionFromCart(int optionId, HttpSession session);

    void addOptionToCart(int contractId, int optionId, HttpSession session);

    Set<OptionDTO> showAvailableOptions(ContractDTO contractDTO, HttpSession session);
}
