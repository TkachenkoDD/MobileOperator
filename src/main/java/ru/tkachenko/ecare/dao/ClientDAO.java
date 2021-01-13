package ru.tkachenko.ecare.dao;

import ru.tkachenko.ecare.models.Client;

import java.util.List;

public interface ClientDAO extends GenericDAO<Client>{

    Object showByName(String name);
}
