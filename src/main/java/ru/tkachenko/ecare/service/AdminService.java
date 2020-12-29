package ru.tkachenko.ecare.service;

import ru.tkachenko.ecare.models.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> showAll();

    Admin showById(int id);

    void save(Admin admin);

    void update(Admin admin);

    void delete(Admin admin, int id);
}
