package com.acabes.training.component3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionsDao {
	
	private final Connection connection;
	private  int counter = 0;
	
	private PreparedStatement preparedStatement;
	public TransactionsDao() throws SQLException {
		String url= "jdbc:mysql://localhost:3306/acabes?rewriteBatchedStatements=true";
		connection = DriverManager.getConnection(url , "root" ,"admin99;");
		preparedStatement = connection.prepareStatement("INSERT INTO user_transactions(from_id, from_name, to_id, to_name, amount) VALUES(?,?,?,?,?)");	
		System.out.println("Connected!");
	}
	public boolean insert(Transaction transaction) throws SQLException {
		
		
		preparedStatement.setInt(1, transaction.getFromId());
		preparedStatement.setString(2, transaction.getFromName());
		preparedStatement.setInt(3, transaction.getToId());
		preparedStatement.setString(4,transaction.getToName());
		preparedStatement.setDouble(5, transaction.getAmount());
		preparedStatement.addBatch();
		
		counter++;
		if(counter % 10000 == 0) {
			preparedStatement.executeBatch();
		}
		
		
		
		return true;		
	}
}
