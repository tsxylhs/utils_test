package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
public class EmailTest_2 {
    //
    private static final String ROOTURL = "https://microsoftgraph.chinacloudapi.cn/v1.0/";

    //
    public static void main(String[] args) throws Exception {
        String token = getAuth();

        //  String url=  "https://microsoftgraph.chinacloudapi.cn/v1.0/me/messages/AAMkADE0ZmQzMzA4LTQyMjItNDE5Yy05ZDk5LTNhYzk2MGY3YTlkZgBGAAAAAABmYGf9rkEhSqZ7iAqOfm9GBwBv4xUfVx3KTaylTeKC-4XUAAAAAAEMAABv4xUfVx3KTaylTeKC-4XUAAChqd6IAAA=/createReplyAll";
//        new AuthorizationCodeProvider();
//        GraphServiceClient graphServiceClient=GraphServiceClient.builder().authenticationProvider();
    }

    //
    public static String getAuth() throws Exception {
        String oauth_url = "https://login.chinacloudapi.cn/a07a5f5d-2xx29551-x9c0e-5b9x8a8a5dffd/oauth2/token";
        String resource_url = "https://microsoftgraph.chinacloudapi.cn";

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "client_credentials");
        parameters.put("resource", resource_url);
//        parameters.put("client_id", "31618fcd-333a-4dd8-b7-a894bc559885");
//        parameters.put("client_secret", "xZ61r-.33VmPu-jHxm571ALbjw8.XE6_");

        HttpURLConnection connection = (HttpURLConnection) new URL(oauth_url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(getParamsString(parameters));
        out.flush();
        out.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            System.out.println("token:" + content.toString());
        } else {
            throw new Exception("Failed to get auth: " + responseCode);
        }

        return oauth_url;
    }

//
//    public static void getUserAmt(String url, String token) throws Exception {
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("POST");
//
//        connection.setRequestProperty("Authorization", "Bearer " + token);
//        System.out.println("token:++>"+token);
//        connection.setRequestProperty("Content-Length","0");
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            connection.disconnect();
//
//            System.out.println(content.toString());
//        } else {
//            throw new Exception("Failed to get user amt: " + responseCode);
//        }
//    }
//
    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }
        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }
}
//
//
