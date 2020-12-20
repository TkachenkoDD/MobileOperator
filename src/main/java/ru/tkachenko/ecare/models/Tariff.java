package ru.tkachenko.ecare.models;


import javax.persistence.*;

@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tariffname")
    private String tariffName;
    @Column(name = "tariffcost")
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
