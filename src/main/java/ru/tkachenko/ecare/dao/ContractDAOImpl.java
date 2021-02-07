package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Contract;

@Repository
public class ContractDAOImpl extends GenericDAOImpl<Contract> implements ContractDAO {

    public ContractDAOImpl() {
        super();
        setEntity(Contract.class);
    }

    @Override

    public Object showClientByNumber(int num) {
        return entityManager.createQuery("FROM Contract WHERE number = :castNumber")
                .setParameter("castNumber", num).getSingleResult();
    }
}
