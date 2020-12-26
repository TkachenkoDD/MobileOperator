package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Contract;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContractDAOImpl implements GenericDAO<Contract> {

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public void delete(Contract contract) {
        contract = entityManager.find(Contract.class, contract.getId());
        entityManager.remove(contract);
    }
}
