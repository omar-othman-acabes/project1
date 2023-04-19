package com.acabes.training.component2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import com.acabes.training.Main;
import com.acabes.training.Utils;

public class Writer {
    public ArrayList<FullTransaction> fullTransactions;
    int rowsNumber;

    public Writer(ArrayList<FullTransaction> fullTransactions) throws IOException {
        this.fullTransactions = fullTransactions;
        writeCSVFile();
    }

    public void writeCSVFile() throws IOException {
        String filePath = Utils.getFullFilePath();

        String directoryPath = new File(filePath).getParent();
        File directory = new File(directoryPath);
        directory.mkdirs();

        // create FileWriter object with file as parameter
        FileWriter writer = new FileWriter(filePath);

        // create CSVWriter object filewriter object as parameter
        writer.append("From Account, From Account Name,To Account, To Account Name ,Amount\n");

        for (int i = 0; i < fullTransactions.size(); i++) {
            String fromAccountName = fullTransactions.get(i).getFromAccountName();
            String toAccountName = fullTransactions.get(i).getToAccountName();
            String fromAccountNumber = String.valueOf(fullTransactions.get(i).getFromAccountNumber());
            String toAccountNumber = String.valueOf(fullTransactions.get(i).getToAccountNumber());
            String amount = String.valueOf(fullTransactions.get(i).getAmount());
            writer.append(fromAccountNumber).append(",");
            writer.append(fromAccountName).append(",");
            writer.append(toAccountNumber).append(",");
            writer.append(toAccountName).append(",");
            writer.append(amount).append("\n");


        }
        writer.close();


    }
}
