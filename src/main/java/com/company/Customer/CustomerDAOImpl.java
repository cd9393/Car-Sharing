package com.company.Customer;

import com.company.Car.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM CUSTOMERS";
    private static final String ADD_CUSTOMER = "INSERT INTO CUSTOMERS (name) VALUES ?";

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:./src/carsharing/db/";
            connection = DriverManager.getConnection(url + "carsharing");
            connection.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CUSTOMERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Customer customer = new Customer(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getInt("RENTED_CAR_ID"));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void addCustomer(String name) {
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(ADD_CUSTOMER);) {
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            System.out.println("The customer was added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
