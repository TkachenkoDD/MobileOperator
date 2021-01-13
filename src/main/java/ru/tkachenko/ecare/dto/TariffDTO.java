package ru.tkachenko.ecare.dto;

import ru.tkachenko.ecare.models.Contract;

import java.util.HashSet;
import java.util.Set;

public class TariffDTO {

    private int id;
    private String tariffName;
    private int tariffCost;
    private Set<Contract> contractSet = new HashSet<>();

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
