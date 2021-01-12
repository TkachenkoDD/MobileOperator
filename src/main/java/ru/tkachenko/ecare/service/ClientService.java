package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.models.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> showAll();

    ClientDTO showById(int id);

    ClientDTO showByName();

    void save(ClientDTO clientDTO);

    void update(ClientDTO clientDTO);

    void delete(ClientDTO clientDTO, int id);

    Client toEntity(ClientDTO clientDTO);
}
