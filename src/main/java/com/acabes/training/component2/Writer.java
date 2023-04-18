package com.acabes.training.component2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Writer {
    public LinkedList<Payment> paymentList = new LinkedList<>();
    int rowsNumber;

    public Writer(LinkedList<Payment> paymentList, int rowsNumber) throws IOException {
        this.paymentList = paymentList;
        this.rowsNumber = rowsNumber;
        writeCSVFile();
    }

    public void writeCSVFile() throws IOException {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the current date in "dd/MM/yyyy" format
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = currentDateTime.format(dateFormat);

        String filePath = "C:\\Users\\THINPAD_12\\Desktop\\" + date.replace("/", "-") + ".csv";
        String directoryPath = new File(filePath).getParent();
        File directory = new File(directoryPath);
        directory.mkdirs();

        FileWriter writer = new FileWriter(filePath);
        writer.append("From Account, From Account Name,To Account, To Account Name ,Amount\n");

        for(int i = 0; i < rowsNumber; i++)
        {


        }



    }
}
