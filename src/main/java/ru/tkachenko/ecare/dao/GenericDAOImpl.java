package ru.tkachenko.ecare.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    @PersistenceContext
    public EntityManager entityManager;



}
