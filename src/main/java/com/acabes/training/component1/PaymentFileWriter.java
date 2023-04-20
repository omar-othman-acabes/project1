package com.acabes.training.component1;

import com.acabes.training.Utils;

import java.io.*;
import java.util.Random;

public class PaymentFileWriter {
    public void createRandomStartingFile(int rows) throws IOException {
        String filePath = Utils.getInitialFilePath();

        // Create the directory if it doesn't exist
        String directoryPath = new File(filePath).getParent();
        File directory = new File(directoryPath);
        directory.mkdirs();

        // Append an empty space to the file
        FileWriter writer = new FileWriter(filePath);
        writer.append("From Account,To Account,Amount\n");
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            int randomnumberone = random.nextInt(1000000 + rows - 100000) + 100000;
            int randomNumbertwo = random.nextInt(1000000 + rows - 100000) + 100000;
            double amount = random.nextInt(991) + 10;
            if (randomnumberone == randomNumbertwo) {
                continue;
            }
            writer.append(String.valueOf(randomnumberone)).append(",");
            writer.append(String.valueOf(randomNumbertwo)).append(",");
            writer.append(String.valueOf(amount)).append("\n");
        }
        writer.close();
    }
}
