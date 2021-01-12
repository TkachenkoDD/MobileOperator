package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Client;

import java.util.List;

@Repository
public class ClientDAOImpl extends GenericDAOImpl<Client> implements ClientDAO {

    public ClientDAOImpl() {
        super();
        setEntity(Client.class);
    }

    @Override
    public Client showByName(String name) {
        Client client = null;
List<Client> clients = entityManager.createQuery("SELECT c FROM Client c WHERE c.email LIKE :castName")
                .setParameter("castName", name).getResultList();
for (Client x: clients)
    client = x;
return client;
    }
}
