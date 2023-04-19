package com.acabes.training.component3;

import com.acabes.training.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class DataHandler {
	private final TransactionsDao transactionsDao;

	public DataHandler() throws SQLException, ClassNotFoundException {
		this.transactionsDao =  TransactionsDao.getInstance();
	}
	public void readFile() throws IOException, SQLException {
		transactionsDao.clearDatabase();


		String PATH = Utils.getFullFilePath();
		BufferedReader bufferedReader = new BufferedReader(new FileReader((PATH)));
		String line;

		bufferedReader.readLine(); //ignore the header
		
		while ((line = bufferedReader.readLine()) != null) {

			String[] values = line.split(",");

			Transaction currentTransaction = new Transaction(new Account(Integer.parseInt(values[0]), values[1]),
					new Account(Integer.parseInt(values[2]), values[3]), Double.parseDouble(values[4]));

			transactionsDao.insert(currentTransaction);
		}
		transactionsDao.executeOnce();
		bufferedReader.close();
		transactionsDao.closeStatement();
	}
}