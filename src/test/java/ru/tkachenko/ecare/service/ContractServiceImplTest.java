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
import ru.tkachenko.ecare.dao.ContractDAO;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.models.Client;
import ru.tkachenko.ecare.models.Contract;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ContractServiceImplTest {

    @Mock
    private ContractDAO contractDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ContractServiceImpl contractService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test() {
        assertNotNull(contractService);
    }

    @Test
    void showAll() {
        Contract contract = new Contract();
        Contract contract1 = new Contract();
        List<Contract> contracts = new ArrayList<>();
        List<ContractDTO> contractDTOList = new ArrayList<>();
        contracts.add(contract);
        contracts.add(contract1);
        when(contractDAO.showAll()).thenReturn(contracts);
        when(modelMapper.map(contracts, new TypeToken<List<ContractDTO>>() {
        }.getType())).thenReturn(contractDTOList);
        assertNotNull(contractService.showAll());
        assertEquals(contractDTOList, contractService.showAll());
    }

    @Test
    void showById() {
        Contract contract = new Contract();
        ContractDTO contractDTO = new ContractDTO();
        when(contractDAO.showById(anyInt())).thenReturn(contract);
        when(modelMapper.map(contract, ContractDTO.class)).thenReturn(contractDTO);
        assertNotNull(contractService.showById(anyInt()));
        assertEquals(contractDTO, contractService.showById(anyInt()));
    }

    @Test
    void showClientByNumber() {
        Client client = new Client();
        ClientDTO clientDTO = new ClientDTO();
        Contract contract = new Contract();
        ContractDTO contractDTO = new ContractDTO();
        contract.setClient(client);
        contractDTO.setClientDTO(clientDTO);
        when(contractDAO.showClientByNumber(any())).thenReturn(contract);
        when(modelMapper.map(contract, ContractDTO.class)).thenReturn(contractDTO);
        when(modelMapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
        assertNotNull(contractService.showClientByNumber(any()));
        assertEquals(contractDTO.getClientDTO(), contractService.showClientByNumber(any()));
    }

    @Test
    void save() {
        ContractDTO contractDTO = new ContractDTO();
        Contract contract = new Contract();
        when(contractService.toEntity(contractDTO)).thenReturn(contract);
        doNothing().when(contractDAO).save(contract);
        assertDoesNotThrow(()-> contractService.save(contractDTO));

    }

    @Test
    void update() {
        ContractDTO contractDTO = new ContractDTO();
        Contract contract = new Contract();
        when(contractService.toEntity(contractDTO)).thenReturn(contract);
        doNothing().when(contractDAO).update(contract);
        assertDoesNotThrow(()-> contractService.update(contractDTO));
    }

    @Test
    void delete() {
        ContractDTO contractDTO = new ContractDTO();
        Contract contract = new Contract();
        when(contractService.toEntity(contractDTO)).thenReturn(contract);
        doNothing().when(contractDAO).delete(any(), anyInt());
        assertDoesNotThrow(()-> contractService.delete(any(), anyInt()));
    }

    @Test
    void contractBlock() {
        Contract contract = new Contract();
        contract.setStatus(1);
        when(contractDAO.showById(anyInt())).thenReturn(contract);
        assertEquals(1, contract.getStatus());
        doNothing().when(contractDAO).update(contract);
    }

    @Test
    void toEntity() {
        Contract contract = new Contract();
        ContractDTO contractDTO = new ContractDTO();
        when(modelMapper.map(contractDTO, Contract.class)).thenReturn(contract);
        assertNotNull(contract);
    }
}