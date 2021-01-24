package ru.tkachenko.ecare.dto;

import ru.tkachenko.ecare.models.enums.Role;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClientDTO {

    private int id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private int numberOfPassport;
    private String address;
    private Set<ContractDTO> contractSetDTO = new HashSet<>();
    private String email;
    private String password;
    private Role role;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getNumberOfPassport() {
        return numberOfPassport;
    }

    public void setNumberOfPassport(int numberOfPassport) {
        this.numberOfPassport = numberOfPassport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<ContractDTO> getContractSetDTO() {
        return contractSetDTO;
    }

    public void setContractSetDTO(Set<ContractDTO> contractSetDTO) {
        this.contractSetDTO = contractSetDTO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof ClientDTO)) {
            return false;
        }
        ClientDTO clientDTO = (ClientDTO) obj;

        return id == clientDTO.id;
    }
}
