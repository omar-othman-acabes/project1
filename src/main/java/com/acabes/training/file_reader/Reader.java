package com.acabes.training.file_reader;

import java.io.*;
import java.util.ArrayList;

import com.acabes.training.Utils;

public class Reader {
    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>(1000);

    public Reader() throws FileNotFoundException {
        readNamesFile();
        readDataLineByLine();
    }

    public void readNamesFile() {

        String line = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(Utils.resourcesPath + "/names.txt"))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                names.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readDataLineByLine() throws FileNotFoundException, NumberFormatException {

        String line = "";

        try (BufferedReader reader = new BufferedReader(
                new FileReader(Utils.getInitialFilePath()))) {

            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int fromAcc = Integer.parseInt(data[0]);
                int toAcc = Integer.parseInt(data[1]);
                double amount = Double.parseDouble(data[2]);
                Transaction transaction = new Transaction(fromAcc, toAcc, amount);
                transactions.add(transaction);

            }
            Mdm mdm = new Mdm(transactions, names);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}