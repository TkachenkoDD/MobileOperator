package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.TariffDAO;
import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {


    private TariffDAO tariffDAO;

    @Autowired
    public TariffServiceImpl(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    @Transactional
    public List<Tariff> showAll() {
        return this.tariffDAO.showAll();
    }

    @Override
    @Transactional
    public Tariff showId(int id) {
        return this.tariffDAO.showId(id);
    }

    @Override
    @Transactional
    public void save(Tariff tariff) {
        this.tariffDAO.save(tariff);
    }

    @Override
    @Transactional
    public void update(Tariff tariff) {
        this.tariffDAO.update(tariff);
    }

    @Override
    @Transactional
    public void delete(Tariff tariff) {
        this.tariffDAO.delete(tariff);
    }
}
