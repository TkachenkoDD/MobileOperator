package ru.tkachenko.ecare.dto;

import java.util.HashSet;
import java.util.Set;

public class OptionDTO {

    private int id;
    private String optionName;
    private int optionCost;
    private int connectionCost;
    private Set<TariffDTO> tariffSet = new HashSet<>();

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

    public Set<TariffDTO> getTariffSet() {
        return tariffSet;
    }

    public void setTariffSet(Set<TariffDTO> tariffSet) {
        this.tariffSet = tariffSet;
    }
}
