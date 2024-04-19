package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameSortingExample {
    public static void main(String[] args) {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("CMS_Member_20190816165823.csv");
        fileNames.add("CMS_Member_20190816165824.csv");
        fileNames.add("CMS_Member_20190816165822.csv");

        Collections.sort(fileNames, new FileNameComparator());

        System.out.println("Sorted file names:");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }

    static class FileNameComparator implements Comparator<String> {
        @Override
        public int compare(String fileName1, String fileName2) {
            String timeString1 = extractTime(fileName1);
            String timeString2 = extractTime(fileName2);

            return timeString1.compareTo(timeString2);
        }

        private String extractTime(String fileName) {
            Pattern pattern = Pattern.compile("\\d{14}");
            Matcher matcher = pattern.matcher(fileName);

            if (matcher.find()) {
                return matcher.group();
            }

            return "";
        }
    }
}