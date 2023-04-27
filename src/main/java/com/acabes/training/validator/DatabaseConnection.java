package com.acabes.training.validator;

import com.acabes.training.database_exporter.TransactionsDao;

import java.sql.*;

public class DatabaseConnection {
    double total;
    int count;
    private final TransactionsDao transactionsDau;


    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        transactionsDau = TransactionsDao.getInstance();
    }

    public MetaData importInfoFromDatabase() throws SQLException {
        Statement statement = transactionsDau.getConnection().createStatement();
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
        transactionsDau.closeConnection();

        return new MetaData(total, count);
    }

}
