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

public class EmailTest_2 {

    private static final String O365TENANTID = "a07a5f5d-2291-4551-9c0e-5b98a8a5dffd";
    private static final String O365APPID = "31618fcd-333a-4dd8-b227-a894bc559885";
    private static final String O365SECRET = "xZ61r-.33VmPu-jHxmLJ571ALbjw8.XE6_";
    private static final String ROOTURL = "https://microsoftgraph.chinacloudapi.cn/v1.0/";

    public static void main(String[] args) throws Exception {
        String token = getAuth();
//        getUserAmt(ROOTURL + "users", token);
    }

    public static String getAuth() throws Exception {
        String oauth_url = "https://login.chinacloudapi.cn/" + O365TENANTID + "/oauth2/token";
        String resource_url = "https://microsoftgraph.chinacloudapi.cn";

        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "client_credentials");
        parameters.put("resource", resource_url);
        parameters.put("client_id", O365APPID);
        parameters.put("client_secret", O365SECRET);

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


    public static void getUserAmt(String url, String token) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);

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

            System.out.println(content.toString());
        } else {
            throw new Exception("Failed to get user amt: " + responseCode);
        }
    }

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


