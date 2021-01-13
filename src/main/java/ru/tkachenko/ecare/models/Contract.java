package ru.tkachenko.ecare.models;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "tariff")
    private String tariff;

    @Column(name = "options")
    private String options;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Contract(){}

    public Contract(int id, int number, String tariff, String options, Client client) {
        this.id = id;
        this.number = number;
        this.tariff = tariff;
        this.options = options;
        this.client = client;
    }

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

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
