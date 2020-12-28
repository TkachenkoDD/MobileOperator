package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

@Repository
public class TariffDAOImpl extends GenericDAOImpl<Tariff> implements TariffDAO {

    @Override
    public void blaBla(Tariff tariff) {
//test of own method of Tariff
    }

    @Override
    public List<Tariff> showAll() {
        return entityManager.createQuery("FROM Tariff").getResultList();
    }

    @Override
    public Tariff showById(int id) {
        return entityManager.find(Tariff.class, id);
    }

    @Override
    public void save(Tariff tariff) {
        entityManager.persist(tariff);
    }

    @Override
    public void update(Tariff tariff) {
        entityManager.merge(tariff);
    }

    @Override
    public void delete(Tariff tariff) {
        tariff = entityManager.find(Tariff.class, tariff.getId());
        entityManager.remove(tariff);
    }
}
