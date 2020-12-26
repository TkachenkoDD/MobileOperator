package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.GenericDAO;
import ru.tkachenko.ecare.models.Contract;

import java.util.List;

@Service
public class ContractServiceImpl implements GenericService<Contract> {

    private GenericDAO<Contract> genericDAO;

    @Autowired
    public ContractServiceImpl(GenericDAO<Contract> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contract> showAll() {
        return genericDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Contract showById(int id) {
        return genericDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Contract contract) {
        genericDAO.save(contract);
    }

    @Override
    @Transactional
    public void update(Contract contract) {
        genericDAO.update(contract);
    }

    @Override
    @Transactional
    public void delete(Contract contract) {
        genericDAO.delete(contract);
    }
}
