package com.acabes.training.component3;
public class Main {
	public static void main(String[] args) {

		try {
			new DataHandler().readFile();
//			TransactionsDao.clearDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
