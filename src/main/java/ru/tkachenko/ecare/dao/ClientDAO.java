package ru.tkachenko.ecare.dao;

import ru.tkachenko.ecare.models.Client;

import java.util.List;

public interface ClientDAO extends GenericDAO<Client>{

    Client showByName(String name);
}
