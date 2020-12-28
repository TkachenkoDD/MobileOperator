package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.ContractDAO;
import ru.tkachenko.ecare.models.Contract;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractDAO contractDAO;

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO) {
        this.contractDAO = contractDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contract> showAll() {
        return contractDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Contract showById(int id) {
        return contractDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Contract contract) {
        contractDAO.save(contract);
    }

    @Override
    @Transactional
    public void update(Contract contract) {
        contractDAO.update(contract);
    }

    @Override
    @Transactional
    public void delete(Contract contract) {
        contractDAO.delete(contract);
    }
}
