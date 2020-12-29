package ru.tkachenko.ecare.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> clazz;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @PersistenceContext
    public EntityManager entityManager;

    public GenericDAOImpl() {
    }


    @Override
    public List<T> showAll() {
        return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    @Override
    public T showById(int id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity, int id) {
        entity = showById(id);
        entityManager.remove(entity);
    }
}
