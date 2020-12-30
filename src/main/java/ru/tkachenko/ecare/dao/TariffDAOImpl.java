package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Tariff;

@Repository
public class TariffDAOImpl extends GenericDAOImpl<Tariff> implements TariffDAO {

    public TariffDAOImpl() {
        super();
        setEntity(Tariff.class);
    }

    @Override
    public void blaBla(Tariff tariff) {

    }
}
