package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.GenericDAO;
import ru.tkachenko.ecare.models.Tariff;

import java.util.List;

@Service
public class TariffServiceImpl implements GenericService<Tariff> {

    private GenericDAO<Tariff> genericDAO;

    @Autowired
    public TariffServiceImpl(GenericDAO<Tariff> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tariff> showAll() {
        return genericDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tariff showById(int id) {
        return genericDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Tariff tariff) {
        genericDAO.save(tariff);
    }

    @Override
    @Transactional
    public void update(Tariff tariff) {
        genericDAO.update(tariff);
    }

    @Override
    @Transactional
    public void delete(Tariff tariff) {
        genericDAO.delete(tariff);
    }
}
