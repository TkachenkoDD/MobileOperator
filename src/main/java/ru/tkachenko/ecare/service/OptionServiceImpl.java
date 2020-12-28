package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.OptionDAO;
import ru.tkachenko.ecare.models.Option;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private OptionDAO optionDAO;

    @Autowired
    public OptionServiceImpl(OptionDAO optionDAO) {
        this.optionDAO = optionDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Option> showAll() {
        return optionDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Option showById(int id) {
        return optionDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Option option) {
        optionDAO.save(option);
    }

    @Override
    @Transactional
    public void update(Option option) {
        optionDAO.update(option);
    }

    @Override
    @Transactional
    public void delete(Option option) {
        optionDAO.delete(option);
    }
}
