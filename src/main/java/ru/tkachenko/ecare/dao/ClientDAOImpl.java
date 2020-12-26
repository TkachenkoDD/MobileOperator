package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientDAOImpl implements GenericDAO<Client> {

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

    @Override
    public void save(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void update(Client client) {
        entityManager.merge(client);
    }

    @Override
    public void delete(Client client) {
        client = entityManager.find(Client.class, client.getId());
        entityManager.remove(client);
    }
}
