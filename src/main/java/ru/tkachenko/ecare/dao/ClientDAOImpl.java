package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Client;

import java.util.List;

@Repository
public class ClientDAOImpl extends GenericDAOImpl<Client> implements ClientDAO {

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
}
