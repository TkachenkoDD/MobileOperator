package ru.tkachenko.ecare.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.models.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TariffDAOImpl implements TariffDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tariff> showAll() {
        return entityManager.createQuery("from Tariff").getResultList();
    }

    @Override
    public Tariff showId(int id) {
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
