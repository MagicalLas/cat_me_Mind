package com.example.haho6.client;

import android.content.ContentValues;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    }

    class NetThread extends Thread{
        @Override
        public void run() {
            handler.post(() -> {
                req();
                //t.setText("Las");
            });

        }
        String url_;
        String req(){
            try {
                URL url = new URL(url_);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                if(connection==null)
                    return null;
                connection.setConnectTimeout(10000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoInput(true);
                int resC = connection.getResponseCode();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}