package com.acabes.training.component3;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		try {
			new DataHandler().readFile();
		} catch (IOException | SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}
}
