package com.example.as3_tp.connection;

import java.sql.*;

/**
 * used to establish connection with the database
 */

public class ConnectionFactory {
    private static Connection connection;

    static {
        connect();
    }

    /**
     * connect method that uses the database's crucial element for connection(url,username,password)
     * uses drivers to know which application is used for database (MySQL)
     */
    private static void connect(){


        String url = "jdbc:mysql://localhost:3306/schooldb";
        String username = "root";
        String password = "Nutell@123";

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("failed" + e.getMessage());
        }
    }

    /**
     * tries to connect to database
     * @return connection
     */

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        }catch (SQLException e) {
            throw new RuntimeException("Error connection" + e.getMessage(), e);
        }
        return connection;
    }

    /**
     * used for executing queries
     * @param query statement facut din accesta
     * @return the result from a query
     */

    public static ResultSet executeQuery(String query) {
        try {
            if(connection == null || connection.isClosed()){
                connect();
            }
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Error query" + e.getMessage(), e);
        }
    }

    /**
     * used for updating tables
     * @param query statement facut din accesta
     */

    public static void executeUpdate(String query) {
        try {
            if(connection == null || connection.isClosed()){
                connect();
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error update" + e.getMessage(), e);
        }
    }

    /**
     * used for closing result sets
     */

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error close" + e.getMessage());
            }
        }
    }
}


