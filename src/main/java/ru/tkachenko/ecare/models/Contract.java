package ru.tkachenko.ecare.models;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "tariff")
    private String tariff;

    @Column(name = "options")
    private String options;

    public Contract(){}

    public Contract(int id, int number, String tariff, String options) {
        this.id = id;
        this.number = number;
        this.tariff = tariff;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
