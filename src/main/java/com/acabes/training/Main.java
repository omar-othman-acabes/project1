package com.acabes.training;

import com.acabes.training.component1.PaymentFileWriter;
import com.acabes.training.component2.Reader;
import com.acabes.training.component3.TransactionsDao;
import com.acabes.training.component4.Timer;
import com.acabes.training.component4.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of records to generate: ");
        int records = scanner.nextInt();

        runComponent1(records); // generate initial payments file.
        runComponent2(); // read initial payments file & rewrite it with the names (create full file).
        runComponent3(); // read full file and upload its data to the database
        runComponent4(); // read data from initial file, full file & database, and make sure they are matching.

        System.out.println("Total elapsed time: " + Timer.getTotalElapsedTime());
    }

    public static void runComponent1(int records) throws IOException {
        Timer.start("Component 1");
        new PaymentFileWriter().createRandomStartingFile(records);
        Timer.stop();
    }

    public static void runComponent2() throws NumberFormatException, FileNotFoundException {
        Timer.start("Component 2");
        new Reader();
        Timer.stop();
    }

    public static void runComponent3() throws SQLException, ClassNotFoundException {
        Timer.start("Component 3");
        TransactionsDao.getInstance().insertCsvFile();
        Timer.stop();
    }

    public static void runComponent4() throws SQLException, IOException, ClassNotFoundException {
        Timer.start("Component 4");
        new Validator().validate();
        Timer.stop();
    }
}