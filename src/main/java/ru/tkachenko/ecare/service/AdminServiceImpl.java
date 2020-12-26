package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.GenericDAO;
import ru.tkachenko.ecare.models.Admin;

import java.util.List;

@Service
public class AdminServiceImpl implements GenericService<Admin>{

    private GenericDAO<Admin> genericDAO;

    @Autowired
    public AdminServiceImpl(GenericDAO<Admin> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> showAll() {
        return genericDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Admin showById(int id) {
        return genericDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Admin admin) {
        genericDAO.save(admin);
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        genericDAO.update(admin);
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        genericDAO.delete(admin);
    }
}
