package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Client;

@Repository
public class ClientDAOImpl extends GenericDAOImpl<Client> implements ClientDAO {

    public ClientDAOImpl() {
        super();
        setEntity(Client.class);
    }

    @Override
    public Object showByName(String name) {
  return entityManager.createQuery("FROM Client WHERE email = :castName")
          .setParameter("castName", name)
          .getSingleResult();
    }
}
