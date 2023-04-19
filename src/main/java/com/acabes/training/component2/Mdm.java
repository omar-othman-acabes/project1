package com.acabes.training.component2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Mdm {

	private final ArrayList<Transaction> initialTransactions ;
	private final ArrayList<FullTransaction> fullTransactions ;

	public Mdm(ArrayList<Transaction> initialTransactions) throws IOException {
		this.initialTransactions = initialTransactions;
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
		String fromAccountName = getAccountName(fromAccountID);
		String toAccountName = getAccountName(toAccountID);
		fullTransactions.add(new FullTransaction(fromAccountID, toAccountID, amount, fromAccountName, toAccountName));
	}

	private String getAccountName(int id) {
		String idString = Integer.toString(id);
		String asciiName = "";
		for (int i = 0; i < idString.length(); i++) {
			int number = idString.charAt(i) + 65;
			char character = (char) number;
			asciiName = asciiName.concat(String.valueOf(character));

		}

		return asciiName;
	}


}
