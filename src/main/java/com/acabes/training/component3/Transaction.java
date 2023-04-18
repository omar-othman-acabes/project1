package com.acabes.training.component3;

public class Transaction {
	private final Account from;
	private final Account to;
	private final double amount;

	public Transaction(Account from, Account to, double amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}

	public Account getFrom() {
		return from;
	}

	public Account getTo() {
		return to;
	}

	public double getAmount() {
		return amount;
	}


	
	
}
