package ru.tkachenko.ecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.AdminDAO;
import ru.tkachenko.ecare.models.Admin;

import java.util.List;

/**
 * Service class for {@link Admin}
 *
 * @author Dmitriy Tkachenko
 * @version 1.0
 */


@Service
public class AdminServiceImpl implements AdminService{

    private AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> showAll() {
        return adminDAO.showAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Admin showById(int id) {
        return adminDAO.showById(id);
    }

    @Override
    @Transactional
    public void save(Admin admin) {
        adminDAO.save(admin);
    }

    @Override
    @Transactional
    public void update(Admin admin) {
        adminDAO.update(admin);
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        adminDAO.delete(admin);
    }
}
