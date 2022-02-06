package com.company.Customer;

import com.company.Car.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM CUSTOMER";
    private static final String ADD_CUSTOMER = "INSERT INTO CUSTOMER (name) VALUES ?";
    private static final String RETURN_CAR = "UPDATE customer SET RENTED_CAR_ID = NULL WHERE ID = ?";
    private static final String RENT_CAR = "UPDATE customer SET RENTED_CAR_ID = ? WHERE ID = ?";
    private static final String GET_CUSTOMER_BY_ID = "SELECT * FROM CUSTOMER WHERE ID = ?";

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
    public Customer getCustomerByID(int id) {
        Customer customer = null;
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_ID);){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                customer = new Customer(resultSet.getInt("ID"), resultSet.getString("name"),resultSet.getInt("RENTED_CAR_ID"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return customer;
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

    @Override
    public void returnRentedCar(int id) {
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(RETURN_CAR);){
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rentACar(int carID, int customerID) {
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(RENT_CAR);){
            preparedStatement.setInt(1,carID);
            preparedStatement.setInt(2,customerID);
            preparedStatement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
