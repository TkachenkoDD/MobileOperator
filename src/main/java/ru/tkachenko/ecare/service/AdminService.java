package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> showAll();

    AdminDTO showById(int id);

    void save(AdminDTO adminDTO);

    void update(AdminDTO adminDTO);

    void delete(AdminDTO adminDTO, int id);
}
