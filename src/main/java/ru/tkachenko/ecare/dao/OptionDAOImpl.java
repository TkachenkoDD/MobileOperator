package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Option;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OptionDAOImpl implements GenericDAO<Option> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Option> showAll() {
        return entityManager.createQuery("FROM Option").getResultList();
    }

    @Override
    public Option showById(int id) {
        return entityManager.find(Option.class, id);
    }

    @Override
    public void save(Option option) {
        entityManager.persist(option);
    }

    @Override
    public void update(Option option) {
        entityManager.merge(option);
    }

    @Override
    public void delete(Option option) {
    option = entityManager.find(Option.class, option.getId());
    entityManager.remove(option);
    }
}
