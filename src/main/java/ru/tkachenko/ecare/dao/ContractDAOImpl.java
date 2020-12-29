package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Contract;

import java.util.List;

@Repository
public class ContractDAOImpl extends GenericDAOImpl<Contract> implements ContractDAO {

    @Override
    public List<Contract> showAll() {
        return entityManager.createQuery("FROM Contract").getResultList();
    }

    @Override
    public Contract showById(int id) {
        return entityManager.find(Contract.class, id);
    }

    @Override
    public void save(Contract contract) {
        entityManager.persist(contract);
    }

    @Override
    public void update(Contract contract) {
        entityManager.merge(contract);
    }
}
