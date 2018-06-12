package com.example.haho6.application;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    LinearLayout area;
    TextView t;

    ArrayList<TextView> array = new ArrayList<>();

    int[][] arr = new int[36][36];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.vv);
        area = findViewById(R.id.area);
        for (int i=0; i <30;i++){
            for (int j=0; j <30;j++){
                arr[i][j]=0;
            }
        }
        ColorChangeHelper color = new ColorChangeHelper(findViewById(R.id.paret));

        for (int j = 0; j < 30; j++) {
            LinearLayout layout = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams Layparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(Layparams);

            for (int i = 0; i < 30; i++) {
                TextView item = new TextView(MainActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(2, 2, 2, 2);
                item.setLayoutParams(params);
                item.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item.setTextSize(0);
                item.setPadding(13, 13, 13, 10);
                layout.addView(item);
                array.add(item);
            }
            area.addView(layout);
        }
        findViewById(R.id.in).setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                t.setText(((int) event.getX() - (int) area.getX()) / 30 + " ::  " + ((int) event.getY() - (int) area.getY()) / 30 + ":");
                int x = ((int) event.getX() - (int) area.getX()) / 30;
                int y = ((int) event.getY() - (int) area.getY()) / 30;
                if (x < 0 || x >= 30 || y < 0 || y >= 30)
                    return false;
                if(arr[x][y]==color.now_color)
                    return true;
                arr[x][y]=color.now_color;
                array.get(y * 30 + x).setBackgroundColor(color.now_color);

                new WebView(this).loadUrl("http://192.168.0.11:8080/A/" + (x < 10 ? "0" + x : x) + "" + (y < 10 ? "0" + y : y) + "" + color.now_color);

                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                t.setText(event.getX() + " ::  " + event.getY());
                return true;
            }
            return false;
        });

        findViewById(R.id.delta).setOnClickListener(v -> {
            array.forEach(x -> {
                x.setBackgroundColor(Color.WHITE);
            });
        });

        findViewById(R.id.ASIN).setOnClickListener(v -> {
            new mThread().start();
        });
    }

    class mThread extends Thread{
        @Override
        public void run() {
            try {
                URL url = new URL("http://192.168.0.11:8080/A");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    readStream(in);
                    urlConnection.disconnect();

                }else{
                    Toast.makeText(getApplicationContext(), "에러발생", Toast.LENGTH_SHORT).show();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void readStream(InputStream in){
        final String data = readData(in);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                t.setText(data);
            }
        });
    }
    public String readData(InputStream is){
        String data = "";
        Scanner s = new Scanner(is);
        while(s.hasNext()) data += s.nextLine() + "\n";
        s.close();
        return data;
    }
    Handler mHandler = new Handler();
}

