package ru.tkachenko.ecare.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> entity;

    public final void setEntity(final Class<T> entityToSet) {
        this.entity = entityToSet;
    }

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<T> showAll() {
        return entityManager.createQuery("FROM " + entity.getName()).getResultList();
    }

    @Override
    public T showById(int id) {
        return entityManager.find(entity, id);
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
