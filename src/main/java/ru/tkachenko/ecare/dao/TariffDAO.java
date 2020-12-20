package ru.tkachenko.ecare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.tkachenko.ecare.models.Tariff;
import ru.tkachenko.ecare.config.HibernateSessionFactoryUtil;

import java.util.List;

@Component
public class TariffDAO {

    public List<Tariff> showAll() {

        List<Tariff> listOfTariffs =
                (List<Tariff>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Tariff").list();
        return listOfTariffs;
    }

    public Tariff showId(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tariff.class, id);
    }

    public void save(Tariff tariff) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(tariff);
        transaction.commit();
        session.close();
    }

    public void update(Tariff updatedTariff) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(updatedTariff);
        transaction.commit();
        session.close();
    }

    public void delete(Tariff tariff) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(tariff);
        transaction.commit();
        session.close();
    }
}
