package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Repository;
import ru.tkachenko.ecare.models.Option;

@Repository
public class OptionDAOImpl extends GenericDAOImpl<Option> implements OptionDAO {

    public OptionDAOImpl() {
        super();
        setClazz(Option.class);
    }
}
