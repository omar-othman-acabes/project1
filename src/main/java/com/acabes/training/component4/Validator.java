package com.acabes.training.component4;

import com.acabes.training.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Validator {
    HashMap<Storage, MetaData> infoMap = new HashMap<>();
    HashMap<Storage, String> pathsMap = new HashMap<>();

    public Validator() {
        setPath(Storage.INITIAL, Utils.getInitialFilePath());
        setPath(Storage.FULL, Utils.getFullFilePath());
    }

    public void setPath(Storage storage, String path) {
        pathsMap.put(storage, path);
    }

    public void importFileData(Storage storage, int colIndex) throws IOException {
        int count = 0;
        double total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(getPath(storage)))) {
            br.readLine(); // ignore header
            for (String line; (line = br.readLine()) != null; ) {
                String[] values = line.split(",");
                total += Double.parseDouble(values[colIndex]);
                count++;
            }
        }

        MetaData metaData = new MetaData(total, count);
        setInfo(storage, metaData);
    }

    public String getPath(Storage storage) {
        return pathsMap.get(storage);
    }

    public void setInfo(Storage storage, MetaData metaData) {
        this.infoMap.put(storage, metaData);
    }

    public int getCount(Storage storage) {
        return infoMap.get(storage).count;
    }

    public double getTotal(Storage storage) {
        return infoMap.get(storage).total;
    }

    public void importInitialFile() throws IOException {
        importFileData(Storage.INITIAL, 2);
    }

    public void importFullFile() throws IOException {
        importFileData(Storage.FULL, 4);
    }

    public void importDatabase() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        setInfo(Storage.DATABASE, dbConnection.importInfoFromDatabase());
    }

    public boolean matchTotal() {
        return getTotal(Storage.INITIAL) == getTotal(Storage.FULL) && getTotal(Storage.INITIAL) == getTotal(Storage.DATABASE);
    }

    public boolean matchCount() {
        return getCount(Storage.INITIAL) == getCount(Storage.FULL) && getCount(Storage.INITIAL) == getCount(Storage.DATABASE);
    }

    public void validate() throws IOException, SQLException {
        importInitialFile();
        importFullFile();
        importDatabase();

        ArrayList<String> errors = matchMetaData();
        if (errors.isEmpty()) {
            System.out.println("Perfect, data is valid across all components!");
        } else {
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }

    /**
     * Checks if metadata is matching across all components.
     * @return arrayList containing all errors if any.
     */
    private ArrayList<String> matchMetaData() {
        ArrayList<String> errors = new ArrayList<>(2);

        if (!matchTotal()) {
            errors.add("Total is NOT matching");
        }

        if (!matchCount()) {
            errors.add("Count is NOT matching");
        }

        return errors;
    }

    enum Storage {
        INITIAL, FULL, DATABASE
    }
}
