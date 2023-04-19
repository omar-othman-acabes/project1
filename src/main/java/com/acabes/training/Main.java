package com.acabes.training;

import com.acabes.training.component1.PaymentFileWriter;
import com.acabes.training.component2.Reader;
import com.acabes.training.component3.DataHandler;
import com.acabes.training.component4.Timer;
import com.acabes.training.component4.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of records to generate: ");
        int records = scanner.nextInt();

        Timer.start("Component 1");
        runComponent1(records);
        Timer.stop();

        Timer.start("Component 2");
        runComponent2();
        Timer.stop();

        Timer.start("Component 3");
        runComponent3();
        Timer.stop();

        Timer.start("Component 4");
        runComponent4();
        Timer.stop();

        System.out.println("Total elapsed time: " + Timer.getTotalElapsedTime());
    }

    public static void runComponent1(int records) throws IOException {
        new PaymentFileWriter().createRandomStartingFile(records);
    }

    public static void runComponent2() throws NumberFormatException, FileNotFoundException {
       new Reader();

    }

    public static void runComponent3() throws SQLException, ClassNotFoundException, IOException {
        new DataHandler().readFile();
    }

    public static void runComponent4() throws SQLException, IOException {
        new Validator().validate();
    }
}