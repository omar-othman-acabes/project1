package com.acabes.training.file_reader;

public class FullTransaction extends Transaction {

	private String fromAccountName;
	private String toAccountName;

	public FullTransaction(int accountFrom, int accountTo, double amount, String fromAccountName,
			String toAccountName) {
		super(accountFrom, accountTo, amount);
		this.fromAccountName = fromAccountName;
		this.toAccountName = toAccountName;
	}

	public String getFromAccountName() {
		return fromAccountName;
	}

	public void setFromAccountName(String fromAccountName) {
		this.fromAccountName = fromAccountName;
	}

	public String getToAccountName() {
		return toAccountName;
	}

	public void setToAccountName(String toAccountName) {
		this.toAccountName = toAccountName;
	}

}
