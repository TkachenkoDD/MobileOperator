package ru.tkachenko.ecare.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ContractDTO {

    private int id;
    private int number;
    private boolean status;
    private TariffDTO tariffDTO;
    private ClientDTO clientDTO;
    private Set<OptionDTO> optionDTOSet = new HashSet<>();

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TariffDTO getTariffDTO() {
        return tariffDTO;
    }

    public void setTariffDTO(TariffDTO tariffDTO) {
        this.tariffDTO = tariffDTO;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public Set<OptionDTO> getOptionDTOSet() {
        return optionDTOSet;
    }

    public void setOptionDTOSet(Set<OptionDTO> optionDTOSet) {
        this.optionDTOSet = optionDTOSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ContractDTO)) {
            return false;
        }
        ContractDTO contractDTO = (ContractDTO) obj;

        return id == contractDTO.id;
    }
}