package com.acabes.training.component2;

import java.io.IOException;
import java.util.LinkedList;

public class Mdm {

	private final LinkedList<Transaction> initialTransactions ;
	private final LinkedList<FullTransaction> fullTransactions ;

	public Mdm(LinkedList<Transaction> initialTransactions) throws IOException {
		this.initialTransactions = initialTransactions;
		fullTransactions = new LinkedList<>();
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
			char character = idString.charAt(i +65);
			asciiName = asciiName.concat(String.valueOf(character));

		}

		return asciiName;
	}


}
