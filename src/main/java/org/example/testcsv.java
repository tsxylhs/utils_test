package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class testcsv {
    public static void main(String[] args) {
        String filePath = "./test";
        readFileName(filePath);

    }


    public static List<String> readFileName(String path) {
        List<String> pathlist = new ArrayList<>();
        String directoryPath = path;
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String newFileName = "new_" + file.getName();
                        csv2csv_new(file.getPath(), path + "/" + "new_" + file.getName());
                        pathlist.add(newFileName);
                    }
                }
            }
        }
        return pathlist;
    }


    //转换
    public static void csv2csv_new(String inputFilePath, String outputFilePath) {


        try (Reader reader = new InputStreamReader(new FileInputStream(inputFilePath), StandardCharsets.UTF_8);
             Writer writer = new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReader(reader);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                csvWriter.writeNext(nextLine);
            }
            File file = new File(inputFilePath);
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);

        }

    }

}
