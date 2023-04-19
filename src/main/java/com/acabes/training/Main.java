package com.acabes.training;

import com.acabes.training.component1.PaymentFileWriter;
import com.acabes.training.component2.Reader;
import com.acabes.training.component3.DataHandler;
import com.acabes.training.component4.Timer;
import com.acabes.training.component4.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Scanner scanner = new Scanner(System.in);
        int records = scanner.nextInt();

        Timer.start("Component 1");
        runComponent1(records);
        Timer.stop();

        Instant start = Instant.now();
        runComponent2();
        Instant end = Instant.now();

        Duration elapsedTime = Duration.between(start, end);
        System.out.println("Component2 Elapsed time: " + elapsedTime.getSeconds());


        start = Instant.now();
        runComponent3();
        end = Instant.now();

        elapsedTime = Duration.between(start, end);
        System.out.println("Component3 Elapsed time: " + elapsedTime.getSeconds());


        start = Instant.now();
        runComponent4();
        end = Instant.now();

        elapsedTime = Duration.between(start, end);
        System.out.println("Component4 Elapsed time: " + elapsedTime.getSeconds());
    }

    public static void runComponent1(int records) throws IOException {
        new PaymentFileWriter().createRandomStartingFile(records);
    }

    public static void runComponent2() throws NumberFormatException, FileNotFoundException {
    	Reader re = new Reader();
		re.readDataLineByLine();

    }

    public static void runComponent3() throws SQLException, ClassNotFoundException, IOException {
        new DataHandler().readFile();
    }

    public static void runComponent4() {
        new Validator().validate();
    }
}