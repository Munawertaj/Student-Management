package com.brainstation23.studentApp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static DbConnector instance;
    private Connection connection;

    private DbConnector() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb",
                "root",
                "bs1810"
        );
    }

    public static synchronized DbConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
