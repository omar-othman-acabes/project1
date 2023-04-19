package com.acabes.training.component4;

import com.acabes.training.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Validator {
    private final HashMap<Storage, MetaData> infoMap = new HashMap<>();
    private final HashMap<Storage, String> pathsMap = new HashMap<>();

    public Validator() {
        setPath(Storage.INITIAL, Utils.getInitialFilePath());
        setPath(Storage.FULL, Utils.getFullFilePath());
    }

    private void setPath(Storage storage, String path) {
        pathsMap.put(storage, path);
    }

    private void importFileData(Storage storage, int colIndex) throws IOException {
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

    private String getPath(Storage storage) {
        return pathsMap.get(storage);
    }

    private void setInfo(Storage storage, MetaData metaData) {
        this.infoMap.put(storage, metaData);
    }

    private int getCount(Storage storage) {
        return infoMap.get(storage).count;
    }

    private double getTotal(Storage storage) {
        return infoMap.get(storage).total;
    }

    private void importInitialFile() throws IOException {
        importFileData(Storage.INITIAL, 2);
    }

    private void importFullFile() throws IOException {
        importFileData(Storage.FULL, 4);
    }

    private void importDatabase() throws SQLException, ClassNotFoundException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        setInfo(Storage.DATABASE, dbConnection.importInfoFromDatabase());
    }

    private boolean matchTotal() {
        return getTotal(Storage.INITIAL) == getTotal(Storage.FULL) && getTotal(Storage.INITIAL) == getTotal(Storage.DATABASE);
    }

    private boolean matchCount() {
        return getCount(Storage.INITIAL) == getCount(Storage.FULL) && getCount(Storage.INITIAL) == getCount(Storage.DATABASE);
    }

    public void validate() throws IOException, SQLException, ClassNotFoundException {
        importInitialFile();
        importFullFile();
        importDatabase();

        ArrayList<String> errors = matchMetaData();
        if (errors.isEmpty()) {
            log("Perfect, data is valid across all components!");
        } else {
            for (String error : errors) {
                log(error);
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

    private enum Storage {
        INITIAL, FULL, DATABASE
    }

    private void log(String msg) {
        System.out.printf("[VALIDATOR]: %s%n", msg);
    }

    private void logErr(String msg) {
        System.err.printf("[VALIDATOR]: %s%n", msg);
    }

}
