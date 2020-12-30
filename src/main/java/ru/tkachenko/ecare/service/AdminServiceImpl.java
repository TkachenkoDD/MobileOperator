package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    @Override
    @Transactional(readOnly = true)
    public List<AdminDTO> showAll() {
        List<AdminDTO> adminDTOList = modelMapper.map(adminDAO.showAll(), new TypeToken<List<AdminDTO>>() {}.getType());
        return adminDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDTO showById(int id) {
        return modelMapper.map(adminDAO.showById(id), AdminDTO.class);
    }

    @Override
    @Transactional
    public void save(AdminDTO adminDTO) {
        admin = toEntity(adminDTO);
        adminDAO.save(admin);
    }

    @Override
    @Transactional
    public void update(AdminDTO adminDTO) {
        admin = toEntity(adminDTO);
        adminDAO.update(admin);
    }

    @Override
    @Transactional
    public void delete(AdminDTO adminDTO, int id) {
        admin = toEntity(adminDTO);
        adminDAO.delete(admin, id);
    }

    public Admin toEntity(AdminDTO dto) {
        return modelMapper.map(dto, Admin.class);
    }
}
