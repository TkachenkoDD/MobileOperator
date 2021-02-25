package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.models.Client;

import java.util.List;

/**
 * Created by Dmitrii Tkachenko
 */
public interface ClientService {

    /**
     * Find list of clients from data base
     */
    List<ClientDTO> showAll();

    /**
     * Find client by Id from data base
     *
     * @param id - client's Id
     */
    ClientDTO showById(int id);

    /**
     * Find client by email from data base
     */
    ClientDTO showByName();

    /**
     * save new client in data base
     */
    void save(ClientDTO clientDTO);

    /**
     * update client
     */
    void update(ClientDTO clientDTO);

    /**
     * Delete client from data base
     *
     * @param clientDTO - object for deleting
     * @param id        - client's Id
     */
    void delete(ClientDTO clientDTO, int id);

    /**
     * Method for converting DTO to entity
     *
     * @param clientDTO - DTO
     * @return client
     */
    Client toEntity(ClientDTO clientDTO);
}
