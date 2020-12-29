package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.models.Client;

import java.util.List;

public interface ClientService {

    List<Client> showAll();

    Client showById(int id);

    void save(Client client);

    void update(Client client);

    void delete(Client client, int id);
}
