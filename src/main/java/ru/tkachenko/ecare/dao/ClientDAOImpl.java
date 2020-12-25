package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Client> showAll() {
        return entityManager.createQuery("FROM Client").getResultList();
    }

    @Override
    public Client showById(int id) {
        return entityManager.find(Client.class, id);
    }
}
