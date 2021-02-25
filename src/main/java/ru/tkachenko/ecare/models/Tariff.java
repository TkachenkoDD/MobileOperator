package ru.tkachenko.ecare.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER)
    private Set<Contract> contractSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tariffs_options",
            joinColumns = {@JoinColumn(name = "tariff_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    private Set<Option> optionAvailableSet = new HashSet<>();

    public Tariff() {
    }

    public Tariff(int id, String tariffName, int tariffCost, Set<Contract> contractSet, Set<Option> optionAvailableSet) {
        this.id = id;
        this.tariffName = tariffName;
        this.tariffCost = tariffCost;
        this.contractSet = contractSet;
        this.optionAvailableSet = optionAvailableSet;
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

    public Set<Option> getOptionAvailableSet() {
        return optionAvailableSet;
    }

    public void setOptionAvailableSet(Set<Option> optionAvailableSet) {
        this.optionAvailableSet = optionAvailableSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Tariff)) {
            return false;
        }
        Tariff tariff = (Tariff) obj;

        return id == tariff.id;
    }
}
