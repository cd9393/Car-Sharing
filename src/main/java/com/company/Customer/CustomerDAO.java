package com.company.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getAllCustomers();
    public Customer getCustomerByID(int id);
    public void addCustomer(String name);
    public void returnRentedCar(int id);
    public void rentACar(int carID, int customerID);
}
