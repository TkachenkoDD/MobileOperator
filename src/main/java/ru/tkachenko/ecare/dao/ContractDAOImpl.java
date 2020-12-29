package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Contract;

@Repository
public class ContractDAOImpl extends GenericDAOImpl<Contract> implements ContractDAO {

    public ContractDAOImpl() {
        super();
        setClazz(Contract.class);
    }
}
