package com.acabes.training;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static final String resourcesPath = "src/main/resources";
    public static String getDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDateTime.format(dateFormat);
    }

    public static String getInitialFilePath() {
        return String.format("%s/payments-%s.csv", Utils.resourcesPath, getDate());
    }
    public static String getFullFilePath() {
        return String.format("%s/payments-%s-full.csv", Utils.resourcesPath, getDate());
    }
}
