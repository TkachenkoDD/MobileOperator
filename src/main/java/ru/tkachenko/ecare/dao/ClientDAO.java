package ru.tkachenko.ecare.dao;

import ru.tkachenko.ecare.models.Client;

import java.util.List;

public interface ClientDAO {

    List<Client> showAll();

    Client showById(int id);
}
