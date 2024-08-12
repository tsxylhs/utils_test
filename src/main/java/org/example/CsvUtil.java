package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvUtil {
    public static <T> List<T> readCSVFile(String fileName, Class<T> clazz){
        try {
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(fileName);
            // Note: the file must be in UTF-8 encoding,
            // UTF-8 BOM (for windows) will lead an error that first column can't be read
            final InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                    StandardCharsets.UTF_8);
            FileReader reader = new FileReader(fileName);
            CSVReader csvReader  = new CSVReader(inputStreamReader);


            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);

            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                    .withType(clazz)
                    //将第一行视为标题行
                    .withMappingStrategy(strategy)
                    .withSeparator('\t')
                    .build();

            return csvToBean.parse();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> List<T> getCsvData(File file, Class<T> clazz) throws FileNotFoundException {
        FileReader fr;

        fr = new FileReader(file);


        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(fr)
                .withSeparator('\t')
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }
    }
