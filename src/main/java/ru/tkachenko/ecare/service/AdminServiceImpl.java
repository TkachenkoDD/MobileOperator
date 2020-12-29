package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.AdminDAO;
import ru.tkachenko.ecare.dto.AdminDTO;
import ru.tkachenko.ecare.models.Admin;

import java.util.List;

/**
 * Service class for {@link Admin}
 *
 * @author Dmitriy Tkachenko
 * @version 1.0
 */

@Service
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;
    private ModelMapper modelMapper;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO, ModelMapper modelMapper) {
        this.adminDAO = adminDAO;
        this.modelMapper = modelMapper;
    }

    Admin admin = new Admin();
//  AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);

    @Override
    @Transactional(readOnly = true)
    public List<Admin> showAll() {
        return adminDAO.showAll();
    }

    @Override
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
    public void delete(Admin admin, int id) {
        adminDAO.delete(admin, id);
    }


    public Admin toEntity(AdminDTO dto) {
        return modelMapper.map(dto, Admin.class);
    }


    public AdminDTO toDto(Admin entity) {
        return modelMapper.map(entity, AdminDTO.class);
    }
}
