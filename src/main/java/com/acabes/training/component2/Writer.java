package com.acabes.training.component2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import com.acabes.training.Main;

public class Writer {
    public LinkedList<FullTransaction> fullTransactions = new LinkedList<>();
    int rowsNumber;

    public Writer(LinkedList<FullTransaction> fullTransactions) throws IOException {
        this.fullTransactions = fullTransactions;
        writeCSVFile();
    }

    public void writeCSVFile() throws IOException {
    	// Get the current date and time
    			LocalDateTime currentDateTime = LocalDateTime.now();
    			// Format the current date in "dd/MM/yyyy" format
    			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    			String date = currentDateTime.format(dateFormat);

    			// Replace "date1" with the actual date in the file path
    			// replace Thinkpad_15 to your user name
    			String filePath = Main.resourcesPath + "/payments-full" + date + ".csv";

        String directoryPath = new File(filePath).getParent();
        File directory = new File(directoryPath);
        directory.mkdirs();

        // create FileWriter object with file as parameter
        FileWriter writer = new FileWriter(filePath);

        // create CSVWriter object filewriter object as parameter
        writer.append("From Account, From Account Name,To Account, To Account Name ,Amount\n");

        for(int i = 0; i < fullTransactions.size(); i++)
        {
            String fromAccountName = fullTransactions.get(i).getFromAccountName();
            String toAccountName = fullTransactions.get(i).getToAccountName();
            String fromAccountNumber = String.valueOf(fullTransactions.get(i).getFromAccountNumber());
            String toAccountNumber = String.valueOf(fullTransactions.get(i).getToAccountNumber());
            String amount = String.valueOf(fullTransactions.get(i).getAmount());
            writer.append(fromAccountName).append(",");
            writer.append(fromAccountNumber).append(",");
            writer.append(toAccountName).append(",");
            writer.append(toAccountNumber).append(",");
            writer.append(amount).append("\n");
            

        }
        writer.close();



    }
}
