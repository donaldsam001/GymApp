package com.example.demopro1;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/login";

        String user = "root";
        String password = "Sam@07072005";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM quantrivien");

            while (resultSet.next()) {
                String userName = resultSet.getString("username"); // Column name should match DB
                String userPassword = resultSet.getString("password");

                System.out.println("Username: " + userName);
                System.out.println("Password: " + userPassword);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
