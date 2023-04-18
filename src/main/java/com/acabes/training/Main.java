package com.acabes.training;

import com.acabes.training.component1.PaymentFileWriter;
import com.acabes.training.component3.DataHandler;
import com.acabes.training.component4.Validator;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static final String resourcesPath = "src/main/resources";
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        runComponent1();
        runComponent2();
        runComponent3();
        runComponent4();
    }

    public static void runComponent1() throws IOException {
        new PaymentFileWriter().createRandomStartingFile();
    }

    public static void runComponent2() {

    }

    public static void runComponent3() throws SQLException, ClassNotFoundException, IOException {
        new DataHandler().readFile();
    }

    public static void runComponent4() {
        new Validator().validate();
    }
}