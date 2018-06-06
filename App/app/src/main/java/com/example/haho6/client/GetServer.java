package com.example.haho6.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetServer {
    public int[][] picture(){
        String url_ = "localhost::8080/A";
        HttpURLConnection connection = null;

        try {
            URL url = new URL(url_);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("get");

            InputStream inputStream = connection.getInputStream();
            

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
