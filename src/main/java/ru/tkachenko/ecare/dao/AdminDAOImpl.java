package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Admin;

@Repository
public class AdminDAOImpl extends GenericDAOImpl<Admin> implements AdminDAO {

    public AdminDAOImpl() {
        super();
        setEntity(Admin.class);
    }
}
