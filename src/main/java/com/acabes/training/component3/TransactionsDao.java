package com.acabes.training.component3;


import java.sql.*;

public class TransactionsDao {

    private static Connection connection;
    private int counter;

    private final PreparedStatement preparedStatement;

    public TransactionsDao() throws SQLException, ClassNotFoundException {
        counter=0;

        String url = "jdbc:mysql://localhost:3306/acabes?rewriteBatchedStatements=true";
        connection = DriverManager.getConnection(url, "root", "1234");
        preparedStatement = connection.prepareStatement("INSERT INTO user_transactions(from_id, from_name, to_id, to_name, amount) VALUES(?,?,?,?,?)");
    }

    public void insert(Transaction transaction) throws SQLException {


        preparedStatement.setInt(1, transaction.getFrom().getAccountId());
        preparedStatement.setString(2, transaction.getFrom().getName());
        preparedStatement.setInt(3, transaction.getTo().getAccountId());
        preparedStatement.setString(4, transaction.getTo().getName());
        preparedStatement.setDouble(5, transaction.getAmount());
        preparedStatement.addBatch();

        counter++;
        if (counter % 10000 == 0) {
            preparedStatement.executeBatch();
        }
    }

    public void executeOnce() throws SQLException {
        preparedStatement.executeBatch();
    }

    public void clearDatabase() throws SQLException {
        String sql = "truncate user_transactions";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }
}
