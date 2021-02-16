package ru.tkachenko.ecare.dto;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class ContractDTO {

    private int id;
    private int number;
    private int status;
    private TariffDTO tariffDTO;
    private ClientDTO clientDTO;
    private Set<OptionDTO> optionDTOSet = new TreeSet<>();

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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