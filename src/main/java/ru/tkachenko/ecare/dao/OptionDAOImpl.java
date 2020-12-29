package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Option;

import java.util.List;

@Repository
public class OptionDAOImpl extends GenericDAOImpl<Option> implements OptionDAO {

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
}
