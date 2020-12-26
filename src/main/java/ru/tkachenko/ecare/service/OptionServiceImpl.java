package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.GenericDAO;
import ru.tkachenko.ecare.models.Option;

import java.util.List;

@Service
public class OptionServiceImpl implements GenericService<Option> {

    private GenericDAO<Option> genericDAO;

    @Autowired
    public OptionServiceImpl(GenericDAO<Option> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Option> showAll() {
        return genericDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Option showById(int id) {
        return genericDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Option option) {
        genericDAO.save(option);
    }

    @Override
    @Transactional
    public void update(Option option) {
        genericDAO.update(option);
    }

    @Override
    @Transactional
    public void delete(Option option) {
        genericDAO.delete(option);
    }
}
