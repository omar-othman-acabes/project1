package com.acabes.training.component4;

import com.acabes.training.Main;
import com.acabes.training.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Validator {
    HashMap<FileName, Info> infoMap = new HashMap<>();
    HashMap<FileName, String> pathsMap = new HashMap<>();

    public void setPath(FileName fileName, String path) {
        pathsMap.put(fileName, path);
    }

    public void importFileData(FileName fileName, int colIndex) throws IOException {
        int count = 0;
        long total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(getPath(fileName)))) {
            String headerLine = br.readLine();
            for (String line; (line = br.readLine()) != null; ) {
                String[] values = line.split(",");
                total += Integer.parseInt(values[colIndex]);
                count++;
            }
        }

        Info info = new Info(total, count);
        setInfo(fileName, info);
    }

    public String getPath(FileName fileName) {
        return pathsMap.get(fileName);
    }

    public void setInfo(FileName fileName, Info info) {
        this.infoMap.put(fileName, info);
    }

    public int getCount(FileName fileName) {
        return infoMap.get(fileName).count;
    }

    public double getTotal(FileName fileName) {
        return infoMap.get(fileName).total;
    }

    public void importStartingFile() throws IOException {
        importFileData(FileName.STARTING, 2);
    }

    public void importFullFile() throws IOException {
        importFileData(FileName.FULL, 4);
    }

    public void importDatabase() throws SQLException, ClassNotFoundException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        setInfo(FileName.DATABASE, dbConnection.importInfoFromDatabase());
    }

    public boolean matchTotal() {
        return getTotal(FileName.STARTING) == getTotal(FileName.FULL) && getTotal(FileName.STARTING) == getTotal(FileName.DATABASE);
    }

    public boolean matchCount() {
        return getCount(FileName.STARTING) == getCount(FileName.FULL);
    }

    public void validate() {
        setPath(FileName.STARTING, Utils.getStartingFilePath());
        setPath(FileName.FULL, Utils.getFullFilePath());

        // import starting file
        try {
            importStartingFile();
        } catch (IOException e) {
            System.out.println("Failed to import starting file.");
            throw new RuntimeException(e);
        }

        // import full file
        try {
            importFullFile();
        } catch (IOException e) {
            System.out.println("Failed to import full file.");
            throw new RuntimeException(e);
        }

        // import database
        try {
            importDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // matching
        if (matchTotal()) {
            System.out.println("Total is matching");
        } else {
            System.out.println("Total is not matching");
        }

        if (matchCount()) {
            System.out.println("Count is matching");
        } else {
            System.out.println("Count is not matching");
        }
    }

    enum FileName {
        STARTING, FULL, DATABASE
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Validator validator = new Validator();
        validator.validate();
    }
}
