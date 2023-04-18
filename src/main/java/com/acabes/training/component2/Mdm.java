package com.acabes.training.component2;

import java.io.IOException;
import java.util.LinkedList;

public class Mdm {

	private LinkedList<Transaction> initialTransactions = new LinkedList<Transaction>();
	private LinkedList<FullTransaction> fullTransactions = new LinkedList<FullTransaction>();

	public Mdm(LinkedList<Transaction> initialTransactions) throws IOException {
		this.initialTransactions = initialTransactions;
		getFullTransactionData();
		Writer writer = new Writer(fullTransactions);
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
		String fromAccountName = getAccountName(fromAccountID);
		String toAccountName = getAccountName(toAccountID);
		fullTransactions.add(new FullTransaction(fromAccountID, toAccountID, amount, fromAccountName, toAccountName));
	}

	private String getAccountName(int id) {
		String idString = Integer.toString(id);
		String asciiName = "";
		for (int i = 0; i < idString.length(); i++) {
			char character = idString.charAt(i);
			asciiName.concat(String.valueOf(character));

		}

		return asciiName;
	}


}
