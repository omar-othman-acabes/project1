package com.acabes.training.component2;

import java.util.LinkedList;

public class Mdm {

	private LinkedList<Transaction> initialTransactions = new LinkedList<Transaction>();
	private LinkedList<FullTransaction> fullTransactions = new LinkedList<FullTransaction>();

	public Mdm(LinkedList<Transaction> initialTransactions) {
		this.initialTransactions = initialTransactions;
	}

	public LinkedList<FullTransaction> getFullPaymentData() {
		for (Transaction transaction : initialTransactions) {
			createFullTransaction(transaction);
		}

		return fullTransactions;
	}

	private void createFullTransaction(Transaction transaction) {
		int fromAccountID = transaction.getAccountFrom();
		int toAccountID = transaction.getAccountTo();
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
