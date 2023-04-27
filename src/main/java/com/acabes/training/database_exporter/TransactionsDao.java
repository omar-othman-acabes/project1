package com.acabes.training.database_exporter;


import com.acabes.training.Utils;

import java.io.File;
import java.sql.*;

public class TransactionsDao {

    public Connection getConnection() {
        return connection;
    }

    private final Connection connection;
    private static TransactionsDao instance;

    public static TransactionsDao getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
            instance = new TransactionsDao();
        return instance;
    }

    private TransactionsDao() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/acabes?rewriteBatchedStatements=true&allowLoadLocalInfile=true";
        connection = DriverManager.getConnection(url, "root", "1234");
    }

    public void insertCsvFile() throws SQLException {
        final String absoluteResourcesPath = new File(Utils.getFullFilePath()).getAbsolutePath().replace("\\", "/");
        clearDatabase();
        connection.createStatement().execute(String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE user_transactions FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n' IGNORE 1 ROWS;",
                        absoluteResourcesPath
                )
        );
    }

    public void clearDatabase() throws SQLException {
        String sql = "truncate user_transactions";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}