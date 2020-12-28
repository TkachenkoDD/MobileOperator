package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.models.Contract;

import java.util.List;

public interface ContractService {

    List<Contract> showAll();

    Contract showById(int id);

    void save(Contract contract);

    void update(Contract contract);

    void delete(Contract contract);
}
