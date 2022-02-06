package com.company.Car;

import com.company.Company.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO{

    private static final String GET_ALL_CARS = "SELECT * FROM car LEFT JOIN customer on car.ID = customer.RENTED_CAR_ID where company_id = ? AND customer.name IS NULL ;";
    private  static final String ADD_CAR = "INSERT INTO car (name, company_id) VALUES (?, ?);";
    private static final String GET_CAR_BY_ID = "SELECT * FROM car WHERE ID = ?";

    public CarDAOImpl() {

    }

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
    public List<Car> getAllCars(int company_id) {
        List<Car> results = new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CARS)) {
            preparedStatement.setInt(1,company_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Car car = new Car(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getInt("company_id"));
                results.add(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void addCar(String name, int company_id) {
        try(Connection connection = getConnection();PreparedStatement preparedStatement = connection.prepareStatement(ADD_CAR);) {
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,company_id);
            preparedStatement.execute();
            System.out.println("The car was added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car getCarByID(int id) {
        Car car = null;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                car = new Car(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getInt("company_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }
}
