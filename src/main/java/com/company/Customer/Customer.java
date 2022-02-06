package com.company.Customer;

public class Customer {
    private int ID;
    private String name;
    private Integer rentedCarId;

    public Customer (int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Customer (int ID, String name, int rentedCarId) {
        this.ID = ID;
        this.name = name;
        this.rentedCarId = rentedCarId;
    }

    public boolean hasRentedCar(){
        System.out.println(this.rentedCarId);
        return this.rentedCarId == 0 ? false : true;
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

    public int getRentedCarId() {
        return rentedCarId;
    }

    public void setRentedCarId(int rentedCarId) {
        this.rentedCarId = rentedCarId;
    }
}
