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
    @Transactional(readOnly = true)
    public List<Tariff> showAll() {
        return tariffDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tariff showById(int id) {
        return tariffDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Tariff tariff) {
        tariffDAO.save(tariff);
    }

    @Override
    @Transactional
    public void update(Tariff tariff) {
        tariffDAO.update(tariff);
    }

    @Override
    @Transactional
    public void delete(Tariff tariff) {
        tariffDAO.delete(tariff);
    }
}
