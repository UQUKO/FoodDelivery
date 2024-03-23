package com.example.database;

import java.sql.*;

public class WeatherData {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:./../weatherdata";
        String username = "admin";
        String password = "ilm";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Connected to H2 embedded database.");

//            deleteTable(connection);
            // Create a table if it doesn't exist
            createTableIfNotExists(connection);

            // Retrieve and print all data from the weatherdata table
            retrieveAndPrintData(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteTable(Connection connection) throws SQLException {
        String sql = "DROP TABLE weatherdata;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
    private static void createTableIfNotExists(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS weatherdata (" +
                "observation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                "station VARCHAR(25) NOT NULL, " +
                "wmocode INT NOT NULL, " +
                "phenomenon VARCHAR(100) NOT NULL, " +
                "airtemperature NUMERIC(4, 1) NOT NULL, " +
                "windspeed NUMERIC(4, 1) NOT NULL, " +
                "CONSTRAINT weather_pk PRIMARY KEY (wmocode, observation_time), " +
                "CONSTRAINT weatherdata_windspeed_check CHECK (windspeed >= 0)" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public static void retrieveAndPrintData(Connection connection) throws SQLException {
        String query = "SELECT * FROM weatherdata " +
                       "ORDER BY observation_time DESC " +
                        "LIMIT 3;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("Last entries: ");
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " " + columnValue);
                }
                System.out.println();
            }
        }
    }
}
