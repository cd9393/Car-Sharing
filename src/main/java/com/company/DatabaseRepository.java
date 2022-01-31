package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRepository {
    private final String databaseFileName;
    private static DatabaseRepository INSTANCE = null;

    private DatabaseRepository(String databaseFileName){
        this.databaseFileName = databaseFileName;
    }

    public static DatabaseRepository getInstance(String databaseFileName) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseRepository(databaseFileName);
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void createDatabase() {
        String sqlCreateDb = "CREATE TABLE IF NOT EXISTS COMPANY(ID INTEGER PRIMARY KEY auto_increment, NAME VARCHAR(64))";
        try(Connection connection = connect(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCreateDb);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:./src/carsharing/db/";
            connection = DriverManager.getConnection(url + databaseFileName);
            connection.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
