package com.company.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO{

    private static final String GET_ALL_COMPANIES = "SELECT * FROM company;";
    private  static final String ADD_COMPANY = "INSERT INTO company (name) VALUES ?";

    public CompanyDAOImpl () {

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
    public List<Company> getAllCompanies() {
        List<Company> results = new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COMPANIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Company company = new Company(resultSet.getInt("ID"), resultSet.getString("name"));
                results.add(company);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void addCompany(String name) {
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(ADD_COMPANY)) {
            preparedStatement.setString(1,name);
            preparedStatement.execute();
            System.out.println("The company was created!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
