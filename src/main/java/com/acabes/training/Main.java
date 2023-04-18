package com.acabes.training;

import com.acabes.training.component1.PaymentFileWriter;
import com.acabes.training.component2.Reader;
import com.acabes.training.component3.DataHandler;
import com.acabes.training.component4.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Instant start = Instant.now();
        runComponent1();
        Instant end = Instant.now();

        Duration elapsedTime = Duration.between(start, end);
        System.out.println("Component1 Elapsed time: " + elapsedTime.getSeconds());

        start = Instant.now();
        runComponent2();
        end = Instant.now();

        elapsedTime = Duration.between(start, end);
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

    public static void runComponent1() throws IOException {
        new PaymentFileWriter().createRandomStartingFile();
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