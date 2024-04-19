package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static void main(String[] args) {
        String startTimeInput = "20231013_22:59:59";
        String endTimeInput = "20231014_23:59:59";

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH:mm:ss");

        LocalDateTime startTime = LocalDateTime.parse(startTimeInput, inputFormatter);
        LocalDateTime endTime = LocalDateTime.parse(endTimeInput, inputFormatter);

        List<String> timeIntervals = splitTimeInterval(startTime, endTime, outputFormatter);

        for (String interval : timeIntervals) {
            System.out.println(interval);
        }
    }

    private static List<String> splitTimeInterval(LocalDateTime startTime, LocalDateTime endTime, DateTimeFormatter formatter) {
        List<String> timeIntervals = new ArrayList<>();

        LocalDateTime currentStart = startTime;
        LocalDateTime currentEnd = currentStart.plusHours(6).withSecond(0).withNano(0);

        while (currentEnd.isBefore(endTime) || currentEnd.isEqual(endTime)) {
            timeIntervals.add(currentStart.format(formatter) + " - " + currentEnd.format(formatter));
            currentStart = currentEnd.plusSeconds(1);
            currentEnd = currentStart.plusHours(6).withSecond(0).withNano(0);
        }

        if (currentStart.isBefore(endTime)) {
            timeIntervals.add(currentStart.format(formatter) + " - " + endTime.format(formatter));
        }

        return timeIntervals;
    }
    }

