package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Component;
import ru.tkachenko.ecare.models.Tariff;

import java.util.ArrayList;
import java.util.List;

@Component
public class TariffDAO {
    private static int TARIFF_COUNT;
    private List<Tariff> listOfTariffs;

    {
        listOfTariffs = new ArrayList<>();
        listOfTariffs.add(new Tariff(++TARIFF_COUNT, "tariff1", 100));
        listOfTariffs.add(new Tariff(++TARIFF_COUNT, "tariff2", 200));
        listOfTariffs.add(new Tariff(++TARIFF_COUNT, "tariff3", 300));
    }

    public List<Tariff> index() {
        return listOfTariffs;
    }

    public Tariff show(int id) {
        return listOfTariffs.stream().filter(tariff -> tariff.getId() == id).findAny().orElse(null);
    }

    public void save(Tariff tariff) {
        tariff.setId(++TARIFF_COUNT);
        listOfTariffs.add(tariff);
    }

    public void update(int id, Tariff updatedTariff){
        Tariff tariffToBeUpdated = show(id);
        tariffToBeUpdated.setTariffName(updatedTariff.getTariffName());
        tariffToBeUpdated.setTariffCost(updatedTariff.getTariffCost());
    }

    public void delete(int id){
        listOfTariffs.removeIf(p -> p.getId() == id);
    }
}
