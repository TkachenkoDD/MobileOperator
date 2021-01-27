package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.models.Contract;

import java.util.List;

public interface ContractService {

    List<ContractDTO> showAll();

    ContractDTO showById(int id);

    void save(ContractDTO contractDTO);

    void update(ContractDTO contractDTO);

    void delete(ContractDTO contractDTO, int id);

    ClientDTO showClientByNumber(int number);

    void contractBlock(int id);

    Contract toEntity(ContractDTO contractDTO);
}
