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
import ru.tkachenko.ecare.dao.TariffDAO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Tariff;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TariffServiceImplTest {

    @Mock
    private TariffDAO tariffDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TariffServiceImpl tariffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test() {
        assertNotNull(tariffService);
    }

    @Test
    void showAll() {
        Tariff tariff = new Tariff();
        Tariff tariff1 = new Tariff();
        List<Tariff> tariffs = new ArrayList<>();
        List<TariffDTO> tariffDTOList = new ArrayList<>();
        tariffs.add(tariff1);
        tariffs.add(tariff);
        when(tariffDAO.showAll()).thenReturn(tariffs);
        when(modelMapper.map(tariffs, new TypeToken<List<TariffDTO>>() {
        }.getType())).thenReturn(tariffDTOList);
        assertNotNull(tariffService.showAll());
        assertEquals(tariffDTOList, tariffService.showAll());
    }

    @Test
    void showById() {
        Tariff tariff = new Tariff();
        TariffDTO tariffDTO = new TariffDTO();
        when(tariffDAO.showById(anyInt())).thenReturn(tariff);
        when(modelMapper.map(tariff, TariffDTO.class)).thenReturn(tariffDTO);
        assertNotNull(tariffService.showById(anyInt()));
        assertEquals(tariffDTO, tariffService.showById(1));
    }

    @Test
    void save() {
        TariffDTO tariffDTO = new TariffDTO();
        Tariff tariff = new Tariff();
        when(tariffService.toEntity(tariffDTO)).thenReturn(tariff);
        doNothing().when(tariffDAO).save(tariff);
    }

    @Test
    void update() {
        TariffDTO tariffDTO = new TariffDTO();
        Tariff tariff = new Tariff();
        when(tariffService.toEntity(tariffDTO)).thenReturn(tariff);
        doNothing().when(tariffDAO).update(tariff);
    }

    @Test
    void delete() {
        TariffDTO tariffDTO = new TariffDTO();
        Tariff tariff = new Tariff();
        when(tariffService.toEntity(tariffDTO)).thenReturn(tariff);
        doNothing().when(tariffDAO).delete(any(), anyInt());
    }

    @Test
    void loadTariffs() {
        Tariff tariff = new Tariff();
        Tariff tariff1 = new Tariff();
        List<Tariff> tariffs = new ArrayList<>();
        List<TariffDTO> tariffDTOList = new ArrayList<>();
        tariffs.add(tariff1);
        tariffs.add(tariff);
        when(tariffDAO.showAll()).thenReturn(tariffs);
        when(modelMapper.map(tariffDAO.showAll(),
                new TypeToken<List<TariffDTO>>() {
                }.getType())).thenReturn(tariffDTOList);
        assertNotNull(tariffService.loadTariffs());
        assertEquals(tariffDTOList, tariffService.loadTariffs());
    }

    @Test
    void toEntity() {
        Tariff tariff = new Tariff();
        TariffDTO tariffDTO = new TariffDTO();
        when(modelMapper.map(tariffDTO, Tariff.class)).thenReturn(tariff);
        assertNotNull(tariff);
    }
}