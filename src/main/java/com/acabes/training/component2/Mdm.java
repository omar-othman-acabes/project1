package com.acabes.training.component2;

import com.acabes.training.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Mdm {

	private final ArrayList<Transaction> initialTransactions ;
	private final ArrayList<FullTransaction> fullTransactions ;
	private final  ArrayList<String> names;
	Random random = new Random();

	public Mdm(ArrayList<Transaction> initialTransactions, ArrayList<String> names) throws IOException {

		this.initialTransactions = initialTransactions;
		this.names = names;

		fullTransactions = new ArrayList<>();
		getFullTransactionData();
		new Writer(fullTransactions);
	}

	public void getFullTransactionData() {
		for (Transaction transaction : initialTransactions) {
			createFullTransaction(transaction);
		}

	}

	private void createFullTransaction(Transaction transaction) {
		int fromAccountID = transaction.getFromAccountNumber();
		int toAccountID = transaction.getToAccountNumber();
		double amount = transaction.getAmount();
		String fromAccountName = getAccountName();
		String toAccountName = getAccountName();
		fullTransactions.add(new FullTransaction(fromAccountID, toAccountID, amount, fromAccountName, toAccountName));
	}

	private String getAccountName() {

		String randomElement = names.get(random.nextInt(names.size()));
		return randomElement;
	}


}
