package ru.tkachenko.ecare.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import ru.tkachenko.ecare.dao.ClientDAO;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.models.Client;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ClientServiceImplTest {

    @Mock
    private ClientDAO clientDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test() {
        assertNotNull(clientService);
    }

    @Test
    void showAll() {
        Client client = new Client();
        Client client1 = new Client();
        List<Client> clients = new ArrayList<>();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        clients.add(client);
        clients.add(client1);
        when(clientDAO.showAll()).thenReturn(clients);
        when(modelMapper.map(clients, new TypeToken<List<ClientDTO>>() {
        }.getType())).thenReturn(clientDTOList);
        assertNotNull(clientService.showAll());
        assertEquals(clientDTOList, clientService.showAll());
    }

    @Test
    void showById() {
        Client client = new Client();
        ClientDTO clientDTO = new ClientDTO();
        when(clientDAO.showById(anyInt())).thenReturn(client);
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
        assertNotNull(clientService.showById(anyInt()));
        assertEquals(clientDTO, clientService.showById(anyInt()));
    }

    @Test
    void save() {
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();
        when(clientService.toEntity(clientDTO)).thenReturn(client);
        doNothing().when(clientDAO).save(client);
    }

    @Test
    void update() {
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();
        when(clientService.toEntity(clientDTO)).thenReturn(client);
        doNothing().when(clientDAO).update(client);
    }

    @Test
    void delete() {
        ClientDTO clientDTO = new ClientDTO();
        Client client = new Client();
        when(clientService.toEntity(clientDTO)).thenReturn(client);
        doNothing().when(clientDAO).delete(any(), anyInt());
    }

    @Test
    void toEntity() {
        Client client = new Client();
        ClientDTO clientDTO = new ClientDTO();
        when(modelMapper.map(clientDTO, Client.class)).thenReturn(client);
        assertNotNull(client);
    }
}