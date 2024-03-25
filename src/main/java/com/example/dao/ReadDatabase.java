package com.example.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class ReadDatabase {

    public static void main(String[] args) {
        int wmocode = 41803;
        List<String> test = retrieveData(wmocode);
    }


    public static List<String> retrieveData(int wmocode) {
        String jdbcURL = "jdbc:h2:./../weatherdata";
        String username = "admin";
        String password = "ilm";
        List<String> info;

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String query = "SELECT * FROM weatherdata " +
                    "WHERE WEATHERDATA.wmocode = " + wmocode +
                    " ORDER BY observation_time DESC " +
                    "LIMIT 1;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            info = resultSetToString(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return info;
    }

    private static List<String> resultSetToString(ResultSet resultSet) throws SQLException {
        List<String> strings = new ArrayList<>();
        int columnCount = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                strings.add(resultSet.getString(i));
            }
        }

        return strings;
    }
}
