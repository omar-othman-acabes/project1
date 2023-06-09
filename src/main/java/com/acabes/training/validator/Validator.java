package com.acabes.training.validator;

import com.acabes.training.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Validator {
    private final HashMap<Storage, MetaData> metaDataMap = new HashMap<>();
    private final HashMap<Storage, String> pathsMap = new HashMap<>();

    public Validator() {
        pathsMap.put(Storage.INITIAL, Utils.getInitialFilePath());
        pathsMap.put(Storage.FULL, Utils.getFullFilePath());
    }

    private void setMetaData(Storage storage, MetaData metaData) {
        metaDataMap.put(storage, metaData);
    }

    private int getCount(Storage storage) {
        return metaDataMap.get(storage).count;
    }

    private double getTotal(Storage storage) {
        return metaDataMap.get(storage).total;
    }

    private void importInitialFile() throws IOException {
        importFileData(Storage.INITIAL, 2);
    }

    private void importFullFile() throws IOException {
        importFileData(Storage.FULL, 4);
    }

    private void importDatabase() throws SQLException, ClassNotFoundException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        setMetaData(Storage.DATABASE, dbConnection.importInfoFromDatabase());
    }

    /**
     * Checks if total amount is matching across all components.
     *
     * @return arrayList of string containing all errors if any.
     */
    private ArrayList<String> matchTotal() {
        ArrayList<String> errors = new ArrayList<>(2);

        if (getTotal(Storage.INITIAL) != getTotal(Storage.FULL)) {
            errors.add("Total amount mismatch between (initial.csv & full.csv)");
        }

        if (getTotal(Storage.FULL) != getTotal(Storage.DATABASE)) {
            errors.add("Total amount mismatch between (full.csv & database)");
        }

        return errors;
    }

    /**
     * Checks if record count is matching across all components.
     *
     * @return arrayList of string containing all errors if any.
     */
    private ArrayList<String> matchCount() {
        ArrayList<String> errors = new ArrayList<>(2);

        if (getCount(Storage.INITIAL) != getCount(Storage.FULL)) {
            errors.add("Record count mismatch between (initial.csv & full.csv)");
        }

        if (getCount(Storage.FULL) != getCount(Storage.DATABASE)) {
            errors.add("Record count mismatch between (full.csv & database)");
        }

        return errors;
    }

    /**
     * Checks if metadata is matching across all components.
     *
     * @return arrayList of string containing all errors if any.
     */
    private ArrayList<String> matchMetaData() {
        ArrayList<String> errors = new ArrayList<>(2);

        errors.addAll(matchTotal());
        errors.addAll(matchCount());

        return errors;
    }

    private void importFileData(Storage file, int colIndex) throws IOException {
        int count = 0;
        double total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(pathsMap.get(file)))) {
            br.readLine(); // skip header
            for (String line; (line = br.readLine()) != null; ) {
                String[] values = line.split(",");
                total += Double.parseDouble(values[colIndex]);
                count++;
            }
        }

        MetaData metaData = new MetaData(total, count);
        setMetaData(file, metaData);
    }

    public void validate() throws IOException, SQLException, ClassNotFoundException {
        importInitialFile();
        importFullFile();
        importDatabase();
        ArrayList<String> errors = matchMetaData();
        printReport(errors);
    }

    private static void printReport(ArrayList<String> errors) {
        if (errors.isEmpty()) {
            log("Perfect, data is valid across all components!");
        } else {
            for (String error : errors) {
                logErr(error);
            }
        }
    }

    private enum Storage {
        INITIAL, FULL, DATABASE
    }

    static void log(String msg) {
        System.out.printf("[VALIDATOR]: %s%n", msg);
    }

    static void logErr(String msg) {
        System.err.printf("[VALIDATOR]: %s%n", msg);
    }
}
