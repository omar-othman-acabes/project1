package com.acabes.training.component2;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import com.acabes.training.Main;
import com.acabes.training.Utils;

public class Reader {
	public static void readDataLineByLine() throws FileNotFoundException, NumberFormatException {
		ArrayList<Transaction> transactions = new ArrayList<>();

		String line = "";

		try (BufferedReader br = new BufferedReader(
				new FileReader(Utils.getStartingFilePath()))) {

			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				int fromAcc = Integer.parseInt(data[0]);
				int toAcc = Integer.parseInt(data[1]);
				double amount = Double.parseDouble(data[2]);
				Transaction transaction = new Transaction(fromAcc, toAcc, amount);
				transactions.add(transaction);

			}
			Mdm mdm = new Mdm(transactions);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}