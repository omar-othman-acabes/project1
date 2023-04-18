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
}
