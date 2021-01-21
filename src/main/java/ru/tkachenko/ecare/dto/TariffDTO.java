package ru.tkachenko.ecare.dto;

import java.util.HashSet;
import java.util.Set;

public class TariffDTO {

    private int id;
    private String tariffName;
    private int tariffCost;
    private Set<ContractDTO> contractSet = new HashSet<>();
    private Set<OptionDTO> optionSet = new HashSet<>();

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

    public Set<ContractDTO> getContractSet() {
        return contractSet;
    }

    public void setContractSet(Set<ContractDTO> contractSet) {
        this.contractSet = contractSet;
    }

    public Set<OptionDTO> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(Set<OptionDTO> optionSet) {
        this.optionSet = optionSet;
    }
}
