package com.acabes.training.component2;

public class Transaction {
	private int accountFrom;
	private int accountTo;
	private double amount;

	public Transaction(int accountFrom, int accountTo, double amount) {
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}