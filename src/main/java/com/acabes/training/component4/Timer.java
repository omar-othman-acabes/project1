package com.acabes.training.component4;

import java.time.Duration;
import java.time.Instant;

public class Timer {
    private static Instant start;
    private static String timerName;
    private static long totalElapsedTime = 0;

    public static long getTotalElapsedTime() {
        return totalElapsedTime;
    }

    public static void start(String timerName) {
        System.out.println(timerName + "started.");
        Timer.timerName = timerName;
        start = Instant.now();
    }

    public static void stop() {
        long elapsedTime = Duration.between(start, Instant.now()).getSeconds();
        System.out.printf("%s elapsed time: %s%n", timerName, elapsedTime);
        totalElapsedTime += elapsedTime;
    }
}
