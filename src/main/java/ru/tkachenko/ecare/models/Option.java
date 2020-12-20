package ru.tkachenko.ecare.models;

public class Option {

    private int id;
    private String optionName;
    private int optionCost;

    public Option(){}

    public Option(int id, String optionName, int optionCost) {
        this.id = id;
        this.optionName = optionName;
        this.optionCost = optionCost;
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
}
