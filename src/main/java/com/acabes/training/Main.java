package com.acabes.training;

import com.acabes.training.file_writer.PaymentFileWriter;
import com.acabes.training.file_reader.Reader;
import com.acabes.training.database_exporter.TransactionsDao;
import com.acabes.training.validator.Timer;
import com.acabes.training.validator.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of records to generate: ");
        int records = scanner.nextInt();

        runFileWriter(records); // generate initial payments file.
        runFileReader(); // read initial payments file & rewrite it with the names (create full file).
        runDatabaseExporter(); // read full file and upload its data to the database
        runValidator(); // read data from initial file, full file & database, and make sure they are matching.

        System.out.println("Total elapsed time: " + Timer.getTotalElapsedTime());
    }

    public static void runFileWriter(int records) throws IOException {
        Timer.start("File Writer");
        new PaymentFileWriter().createRandomStartingFile(records);
        Timer.stop();
    }

    public static void runFileReader() throws NumberFormatException, FileNotFoundException {
        Timer.start("File Reader");
        new Reader();
        Timer.stop();
    }

    public static void runDatabaseExporter() throws SQLException, ClassNotFoundException {
        Timer.start("Database Exporter");
        TransactionsDao.getInstance().insertCsvFile();
        Timer.stop();
    }

    public static void runValidator() throws SQLException, IOException, ClassNotFoundException {
        Timer.start("Validator");
        new Validator().validate();
        Timer.stop();
    }
}