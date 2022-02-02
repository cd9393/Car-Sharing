package com.company.Car;

public class Car {
    private int ID;
    private String name;
    private int company_id;

    public Car(int ID, String name, int company_id) {
        this.ID = ID;
        this.name = name;
        this.company_id = company_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
