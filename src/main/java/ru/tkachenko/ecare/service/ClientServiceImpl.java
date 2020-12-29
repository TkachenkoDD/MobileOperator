package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.ClientDAO;
import ru.tkachenko.ecare.models.Client;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientDAO clientDAO;

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> showAll() {
        return clientDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client showById(int id) {
        return clientDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    @Transactional
    public void update(Client client) {
        clientDAO.update(client);
    }

    @Override
    @Transactional
    public void delete(Client client, int id) {
        clientDAO.delete(client, id);
    }
}
