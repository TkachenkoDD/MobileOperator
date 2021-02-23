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
import ru.tkachenko.ecare.dao.OptionDAO;
import ru.tkachenko.ecare.dto.OptionDTO;
import ru.tkachenko.ecare.models.Option;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class OptionServiceImplTest {

    @Mock
    private OptionDAO optionDAO;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OptionServiceImpl optionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test() {
        assertNotNull(optionService);
    }

    @Test
    void showAll() {
        Option option = new Option();
        Option option1 = new Option();
        List<Option> optionList = new ArrayList<>();
        List<OptionDTO> optionDTOList = new ArrayList<>();
        optionList.add(option);
        optionList.add(option1);
        when(optionDAO.showAll()).thenReturn(optionList);
        when(modelMapper.map(optionList, new TypeToken<List<OptionDTO>>() {
        }.getType())).thenReturn(optionDTOList);
        assertNotNull(optionService.showAll());
        assertEquals(optionDTOList, optionService.showAll());
    }

    @Test
    void showById() {
        Option option = new Option();
        OptionDTO optionDTO = new OptionDTO();
        when(optionDAO.showById(anyInt())).thenReturn(option);
        when(modelMapper.map(option, OptionDTO.class)).thenReturn(optionDTO);
        assertNotNull(optionService.showById(anyInt()));
        assertEquals(optionDTO, optionService.showById(anyInt()));
    }

    @Test
    void save() {
        OptionDTO optionDTO = new OptionDTO();
        Option option = new Option();
        when(optionService.toEntity(optionDTO)).thenReturn(option);
        doNothing().when(optionDAO).save(option);
    }

    @Test
    void update() {
        OptionDTO optionDTO = new OptionDTO();
        Option option = new Option();
        when(optionService.toEntity(optionDTO)).thenReturn(option);
        doNothing().when(optionDAO).update(option);
    }

    @Test
    void delete() {
        OptionDTO optionDTO = new OptionDTO();
        Option option = new Option();
        when(optionService.toEntity(optionDTO)).thenReturn(option);
        doNothing().when(optionDAO).delete(any(), anyInt());
    }

    @Test
    void toEntity() {
        Option option = new Option();
        OptionDTO optionDTO = new OptionDTO();
        when(modelMapper.map(optionDTO, Option.class)).thenReturn(option);
        assertNotNull(option);
    }
}