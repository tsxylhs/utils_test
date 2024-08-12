package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class testcsv {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {





        ExpiringMap<String , String > expiringMap = new ExpiringMap<>(5,TimeUnit.SECONDS,1,TimeUnit.SECONDS );
        expiringMap.put("test","null");
        Thread.sleep(3000);
        expiringMap.put("test","更新");
        Thread.sleep(1000);
        expiringMap.put("test1","1");
        Thread.sleep(3000);
        expiringMap.put("test1","123234");
        Thread.sleep(1000);
        expiringMap.put("test2","12");
        Thread.sleep(1000);
        expiringMap.put("test3","test3");
        Thread.sleep(1000);
        expiringMap.put("test4","test4");
        Thread.sleep(1000);
        Thread.sleep(1000);
        System.out.println(expiringMap.get("test")); // 输出: value1
        System.out.println(expiringMap.get("test1")); // 输出: value1
//        Thread.sleep(14000);
        System.out.println(expiringMap.get("test2")); // 输出: value1
//        Thread.sleep(15000);
        System.out.println(expiringMap.get("test3")); // 输出: value1
//        Thread.sleep(16000);
        System.out.println(expiringMap.get("test4")); // 输出: value1
//        System.out.println(expiringMap.get("test")); // 输出: value1
//        System.out.println(expiringMap.get("test1")); // 输出: value1
//        System.out.println(expiringMap.get("test2")); // 输出: value1
//        System.out.println(expiringMap.get("test3")); // 输出: value1
//        System.out.println(expiringMap.get("test4")); // 输出: value1


   //{"code":"0000","msg":"??","data":"{\"slide_detect\":[{\"image\":\"/images/fod/cc3b43854cfe41e7a6bfebb44336f675.jpg\",\"result\":[{\"confidence\":0.52281665802002,\"detect\":{\"top\":608,\"left\":2004,\"width\":54,\"height\":104},\"targetType\":4,\"attributes\":[{\"value\":\"unK\",\"confidence\":0.52281666,\"key\":\"slide_detect\"}],\"imageId\":\"cc3b43854cfe41e7a6bfebb44336f675\"},{\"confidence\":0.99999213218689,\"detect\":{\"top\":648,\"left\":1688,\"width\":98,\"height\":166},\"targetType\":4,\"attributes\":[{\"value\":\"unK\",\"confidence\":0.99999213,\"key\":\"slide_detect\"}],\"imageId\":\"cc3b43854cfe41e7a6bfebb44336f675\"}]}]}","success":true}
//        final List<MemberRecord> memberRecordList=CsvUtil.readCSVFile("./test.csv",MemberRecord.class);
//        log.info("memberRecordList: {}", memberRecordList);
//        final List<MemberRecord> memberRecordList= CsvUtil.getCsvData(new File("./CMS_Member_FR_20240529120216.csv"),MemberRecord.class);;
//        log.info("memberRecordList: {}", memberRecordList);
//        long elaspedTime=- order.getCreateTime().getTime();
//        Integer  l=(int)(elaspedTime/1000/60/60/orders.size());

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
