package ru.tkachenko.ecare.models;

import ru.tkachenko.ecare.models.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "clientname")
    private String name;

    @Column(name = "clientsurname")
    private String surname;

    @Column(name = "clientbirthday")
    private String dateOfBirth;

    @Column(name = "passport")
    private int numberOfPassport;

    @Column(name = "clientaddress")
    private String address;

    @Column(name = "clientcontracts")
    private String contractList;

    @Column(name = "clientemail")
    private String email;

    @Column(name = "clientpassword")
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Client(){}

    public Client(int id, String name, String surname, String dateOfBirth, int numberOfPassport, String address, String contractList, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.numberOfPassport = numberOfPassport;
        this.address = address;
        this.contractList = contractList;
        this.email = email;
        this.password = password;
        this.role = role;
    }

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

    public String getContractList() {
        return contractList;
    }

    public void setContractList(String contractList) {
        this.contractList = contractList;
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
}
