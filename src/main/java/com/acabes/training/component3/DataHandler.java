package com.acabes.training.component3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class DataHandler {
	private final TransactionsDao transactionsDao;

	public DataHandler() throws SQLException {
		this.transactionsDao = new TransactionsDao();
	}

	private final String PATH = "C:/Users/THINKPAD_6/Downloads/MOCK_DATA.csv";

	public void readFile() throws IOException, SQLException {
		

		BufferedReader bufferedReader = new BufferedReader(new FileReader((PATH)));
		String line = bufferedReader.readLine();
		
		long startTime = System.currentTimeMillis() /1000;
		while ((line = bufferedReader.readLine()) != null) {

			String[] values = line.split(",");
			Transaction currentTransaction = new Transaction(Integer.parseInt(values[0]), values[1],
					Integer.parseInt(values[2]), values[3], Double.parseDouble(values[4]));
			transactionsDao.insert(currentTransaction);

		}
		bufferedReader.close();
		long totalTime = System.currentTimeMillis() /1000 - startTime;
		
		System.out.println("Elapsed Time: " + totalTime);
	}

}
