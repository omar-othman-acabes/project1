package com.acabes.training.component2;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import com.acabes.training.Main;

public class Reader {
	public static void readDataLineByLine() throws FileNotFoundException, NumberFormatException {
		LinkedList<Transaction> transactions = new LinkedList<>();

		String line = "";
		LocalDateTime currentDateTime = LocalDateTime.now();

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date = currentDateTime.format(dateFormat);

		try (BufferedReader br = new BufferedReader(
				new FileReader(Main.resourcesPath + "\\payments-" + date + ".csv"))) {
			System.out.println(java.time.LocalDate.now());

			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				System.out.println(line);
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