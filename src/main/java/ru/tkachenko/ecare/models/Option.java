package ru.tkachenko.ecare.models;

import javax.persistence.*;
import java.util.HashSet;
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

//    @ManyToMany(mappedBy = "optionSet")
//    private Set<Tariff> tariffSet = new HashSet<>();

    public Option() {
    }

    public Option(int id, String optionName, int optionCost, int connectionCost) {
        this.id = id;
        this.optionName = optionName;
        this.optionCost = optionCost;
        this.connectionCost = connectionCost;
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
}
