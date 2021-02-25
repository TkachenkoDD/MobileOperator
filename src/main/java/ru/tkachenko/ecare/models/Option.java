package ru.tkachenko.ecare.models;

import ru.tkachenko.ecare.models.enums.Category;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "optionname")
    private String optionName;

    @Column(name = "optioncost")
    private int optionCost;

    @Column(name = "connectioncost")
    private int connectionCost;

    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToMany(mappedBy = "optionAvailableSet")
    private Set<Tariff> tariffSet = new HashSet<>();

    @ManyToMany(mappedBy = "optionSet")
    private Set<Contract> contractSet = new HashSet<>();

    public Option() {
    }

    public Option(int id, String optionName, int optionCost, int connectionCost, Category category, Set<Tariff> tariffSet, Set<Contract> contractSet) {
        this.id = id;
        this.optionName = optionName;
        this.optionCost = optionCost;
        this.connectionCost = connectionCost;
        this.category = category;
        this.tariffSet = tariffSet;
        this.contractSet = contractSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getOptionCost() {
        return optionCost;
    }

    public void setOptionCost(int optionCost) {
        this.optionCost = optionCost;
    }

    public int getConnectionCost() {
        return connectionCost;
    }

    public void setConnectionCost(int connectionCost) {
        this.connectionCost = connectionCost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tariff> getTariffSet() {
        return tariffSet;
    }

    public void setTariffSet(Set<Tariff> tariffSet) {
        this.tariffSet = tariffSet;
    }

    public Set<Contract> getContractSet() {
        return contractSet;
    }

    public void setContractSet(Set<Contract> contractSet) {
        this.contractSet = contractSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof Option)) {
            return false;
        }
        Option option = (Option) obj;

        return id == option.id;
    }
}
