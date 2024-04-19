package org.example;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Main {
    public static void main(String[] args) {


        FileInputStream fileInputStream = null;
        CSVReader csvReader = null;
        try {
            fileInputStream = new FileInputStream("./test.csv");
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            byte[] bytes = new byte[3];
            bis.mark(3);
            bis.read(bytes, 0, 3);
            bis.reset();
            bis.close();


            if (bytes[0] == (byte) 0xEF && bytes[1] == (byte) 0xBB && bytes[2] == (byte) 0xBF) {
                System.out.println("文件格式为UTF-8带BOM");
            } else {
                System.out.println("文件格式为UTF-8");
            }
            // Note: the file must be in UTF-8 encoding,
            // UTF-8 BOM (for windows) will lead an error that first column can't be read
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                    StandardCharsets.UTF_8);
            CSVParser csvParser = new CSVParserBuilder().withSeparator('\t').build();
            csvReader = new CSVReaderBuilder(inputStreamReader).withCSVParser(csvParser).build();

            final CsvToBean<MemberRecord> csvToBean = new CsvToBeanBuilder(csvReader)
                    .withType(MemberRecord.class)
                    .withIgnoreEmptyLine(true)
                    .build();
           System.out.println( csvToBean.parse().toString());

        } catch (Exception ex) {

        } finally {
            if (fileInputStream != null) {
                try {

                    fileInputStream.close();
                } catch (IOException ex) {

                }
            }
            if (csvReader != null) {
                try {
                    csvReader.close();
                } catch (IOException ex) {

                }
            }
        }
    }
}