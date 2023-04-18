package com.acabes.training.component3;


import java.sql.*;

public class TransactionsDao {

    private static Connection connection;
    private int counter = 0;

    private PreparedStatement preparedStatement;

    public TransactionsDao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/acabes?rewriteBatchedStatements=true";
        connection = DriverManager.getConnection(url, "root", "1234");
//		connection = DriverManager.getConnection(url , "root" ,"admin99;"); // old
        preparedStatement = connection.prepareStatement("INSERT INTO user_transactions(from_id, from_name, to_id, to_name, amount) VALUES(?,?,?,?,?)");
    }

    public void insert(Transaction transaction) throws SQLException {


        preparedStatement.setInt(1, transaction.getFromId());
        preparedStatement.setString(2, transaction.getFromName());
        preparedStatement.setInt(3, transaction.getToId());
        preparedStatement.setString(4, transaction.getToName());
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

    public static void clearDatabase() throws SQLException {
        String sql = "delete from user_transactions limit 2500000";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }
}
