package com.libsysfrontendcustomer.libsysfrontendcustomer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {

    private HttpURLConnection connection;

    public String sendGetRequest(String request){
        String responseString = "";
        String line;

        try {
            URL url = new URL("http://localhost:8080/" + request);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("Status: " + status);

            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line= reader.readLine()) != null){
                    responseString += line;
                }
                reader.close();
                System.out.println();
        }
    }catch (Exception e){
        e.printStackTrace();
        }
        return responseString;
    }

    public String sendPostRequest(String request){
        String responseString = "";
        String line;

        try {
            URL url = new URL("http://localhost:8080/" + request);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("Status: " + status);

            if (status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line= reader.readLine()) != null){
                    responseString += line;
                }
                reader.close();
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseString;
    }
}