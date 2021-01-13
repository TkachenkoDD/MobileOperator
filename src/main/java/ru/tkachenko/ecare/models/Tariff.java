package ru.tkachenko.ecare.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tariffs")
public class Tariff {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tariffname")
    private String tariffName;

    @Column(name = "tariffcost")
    private int tariffCost;

    @OneToMany(mappedBy = "tariff")
    private Set<Contract> contractSet = new HashSet<>();


    public Tariff(){}

    public Tariff(int id, String tariffName, int tariffCost, Set<Contract> contractSet) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffCost = tariffCost;
        this.contractSet = contractSet;
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

    public Set<Contract> getContractSet() {
        return contractSet;
    }

    public void setContractSet(Set<Contract> contractSet) {
        this.contractSet = contractSet;
    }
}
