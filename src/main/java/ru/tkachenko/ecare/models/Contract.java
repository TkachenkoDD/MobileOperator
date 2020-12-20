package ru.tkachenko.ecare.models;

public class Contract {

    private int id;
    private String tariff;
    private String options;

    public Contract(){}

    public Contract(int id, String tariff, String options) {
        this.id = id;
        this.tariff = tariff;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
