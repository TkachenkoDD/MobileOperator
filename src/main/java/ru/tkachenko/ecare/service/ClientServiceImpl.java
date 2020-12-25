package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.GenericDAO;
import ru.tkachenko.ecare.models.Client;

import java.util.List;

@Service
public class ClientServiceImpl implements GenericService<Client> {

    private GenericDAO<Client> genericDAO;

    @Autowired
    public ClientServiceImpl(GenericDAO<Client> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> showAll() {
        return genericDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client showById(int id) {
        return this.genericDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Client client) {
        genericDAO.save(client);
    }

    @Override
    @Transactional
    public void update(Client client) {
        genericDAO.update(client);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        genericDAO.delete(client);
    }
}
