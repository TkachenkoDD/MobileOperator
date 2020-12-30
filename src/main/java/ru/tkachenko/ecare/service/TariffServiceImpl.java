package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.TariffDAO;
import ru.tkachenko.ecare.dto.TariffDTO;
import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {

    private final TariffDAO tariffDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public TariffServiceImpl(TariffDAO tariffDAO, ModelMapper modelMapper) {
        this.tariffDAO = tariffDAO;
        this.modelMapper = modelMapper;
    }

    Tariff tariff = new Tariff();

    @Override
    @Transactional(readOnly = true)
    public List<TariffDTO> showAll() {
        return modelMapper.map(tariffDAO.showAll(), new TypeToken<List<TariffDTO>>() {}.getType());
    }

    @Override
    @Transactional(readOnly = true)
    public TariffDTO showById(int id) {
        return modelMapper.map(tariffDAO.showById(id), TariffDTO.class);
    }

    @Override
    @Transactional
    public void save(TariffDTO tariffDTO) {
        tariff = toEntity(tariffDTO);
        tariffDAO.save(tariff);
    }

    @Override
    @Transactional
    public void update(TariffDTO tariffDTO) {
        tariff = toEntity(tariffDTO);
        tariffDAO.update(tariff);
    }

    @Override
    @Transactional
    public void delete(TariffDTO tariffDTO, int id) {
        tariff = toEntity(tariffDTO);
        tariffDAO.delete(tariff, id);
    }

    @Override
    public Tariff toEntity(TariffDTO tariffDTO) {
        return modelMapper.map(tariffDTO, Tariff.class);
    }
}
