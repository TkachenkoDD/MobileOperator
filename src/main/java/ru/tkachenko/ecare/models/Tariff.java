package ru.tkachenko.ecare.models;

public class Tariff {
    private int id;
    private String tariffName;
    private int tariffCost;

    public Tariff(){}

    public Tariff(int id, String tariffName, int tariffCost) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffCost = tariffCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public int getTariffCost() {
        return tariffCost;
    }

    public void setTariffCost(int tariffCost) {
        this.tariffCost = tariffCost;
    }
}
