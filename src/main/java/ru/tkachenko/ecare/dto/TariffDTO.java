package ru.tkachenko.ecare.dto;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TariffDTO implements Serializable, Comparable<TariffDTO>{

    private int id;
    private String tariffName;
    private int tariffCost;
    private Set<ContractDTO> ContractSet = new HashSet<>();
    private Set<OptionDTO> optionAvailableSet = new TreeSet<>();
    private boolean isHot;

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
        return ContractSet;
    }

    public void setContractSet(Set<ContractDTO> contractSet) {
        ContractSet = contractSet;
    }

    public Set<OptionDTO> getOptionAvailableSet() {
        return optionAvailableSet;
    }

    public void setOptionAvailableSet(Set<OptionDTO> optionAvailableSet) {
        this.optionAvailableSet = optionAvailableSet;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof TariffDTO)) {
            return false;
        }
        TariffDTO tariffDTO = (TariffDTO) obj;

        return id == tariffDTO.id;
    }

    @Override
    public int compareTo(TariffDTO o) {
        return this.tariffName.compareTo(o.tariffName);
    }
}