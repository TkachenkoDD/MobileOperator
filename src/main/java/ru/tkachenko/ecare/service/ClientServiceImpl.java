package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.ClientDAO;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.models.Client;
import ru.tkachenko.ecare.models.enums.Role;

import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;
    private final ModelMapper modelMapper;
    private final ModelMapper mapper;
   private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO, ModelMapper modelMapper, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.clientDAO = clientDAO;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    Client client = new Client();

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> showAll() {
        return modelMapper.map(clientDAO.showAll(), new TypeToken<List<ClientDTO>>() {}.getType());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO showById(int id) {
        Client client = clientDAO.showById(id);
        ClientDTO clientDTO = modelMapper.map(clientDAO.showById(id), ClientDTO.class);
        Set<ContractDTO> contractDTOSet = mapper.map(client.getContractSet(), new TypeToken<Set<ContractDTO>>() {}.getType());
        clientDTO.setContractSetDTO(contractDTOSet);
        return clientDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO showByName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Client client = (Client) clientDAO.showByName(currentName);
        ClientDTO clientDTO = modelMapper.map(clientDAO.showByName(currentName), ClientDTO.class);
        Set<ContractDTO> contractDTOSet = mapper.map(client.getContractSet(), new TypeToken<Set<ContractDTO>>() {}.getType());
        clientDTO.setContractSetDTO(contractDTOSet);
        return clientDTO;
    }

    @Override
    @Transactional
    public void save(ClientDTO clientDTO) {
        clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        clientDTO.setRole(Role.USER);
        client = toEntity(clientDTO);
        clientDAO.save(client);
    }

    @Override
    @Transactional
    public void update(ClientDTO clientDTO) {
        client = toEntity(clientDTO);
        clientDAO.update(client);
    }

    @Override
    @Transactional
    public void delete(ClientDTO clientDTO, int id) {
        client = toEntity(clientDTO);
        clientDAO.delete(client, id);
    }

    @Override
    public Client toEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }
}