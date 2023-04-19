package com.acabes.training.component2;

import java.io.*;
import java.util.ArrayList;

import com.acabes.training.Utils;

public class Reader {
	ArrayList<Transaction> transactions = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();

	public Reader() throws FileNotFoundException {
		readDataLineByLine();
		readNamesFile();
	}
	public void readNamesFile() {

		String line = "";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("names.csv");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				names.add(line.toString());

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public  void readDataLineByLine() throws FileNotFoundException, NumberFormatException {

		String line = "";

		try (BufferedReader br = new BufferedReader(
				new FileReader(Utils.getInitialFilePath()))) {

			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				int fromAcc = Integer.parseInt(data[0]);
				int toAcc = Integer.parseInt(data[1]);
				double amount = Double.parseDouble(data[2]);
				Transaction transaction = new Transaction(fromAcc, toAcc, amount);
				transactions.add(transaction);

			}
			Mdm mdm = new Mdm(transactions, names);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}