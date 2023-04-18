package com.acabes.training.component4;

import java.sql.*;

public class DatabaseConnection {

    private final Connection connection;

    double total;
    int count;

    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/acabes?rewriteBatchedStatements=true";
        connection = DriverManager.getConnection(url, "root", "1234");
    }

    public Info importInfoFromDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user_transactions");

        double total = 0;
        int count = 0;
        while (resultSet.next()) {
            double amount = resultSet.getDouble("amount");
            total += amount;
            count++;
        }

        this.count = count;
        this.total = total;

        resultSet.close();
        statement.close();
        connection.close();

        return new Info(total, count);
    }

}
