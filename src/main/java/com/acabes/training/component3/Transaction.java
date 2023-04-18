package com.acabes.training.component3;

public class Transaction {
	private int fromId;
	private String fromName;
	private int toId;
	private String toName;
	private double amount;
	
	public Transaction(int fromId, String fromName, int toId, String toName, double amount) {
		this.fromId = fromId;
		this.fromName = fromName;
		this.toId = toId;
		this.toName = toName;
		this.amount = amount;
	}
	
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
}
