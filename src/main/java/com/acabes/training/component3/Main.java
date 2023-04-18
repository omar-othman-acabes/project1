package com.acabes.training.component3;
public class Main {
	public static void main(String[] args) {
		try {
			new DataHandler().readFile();			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		CREATE TABLE `user_transactions` (
//				  `id` int NOT NULL AUTO_INCREMENT,
//				  `from_id` int DEFAULT NULL,
//				  `from_name` varchar(255) DEFAULT NULL,
//				  `to_id` int DEFAULT NULL,
//				  `to_name` varchar(255) DEFAULT NULL,
//				  `amount` double DEFAULT NULL,
//				  PRIMARY KEY (`id`)
//				);

		
	}
}
