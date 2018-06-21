package com.example.haho6.application;


import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
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


public class La extends AppCompatActivity {

    LinearLayout area;
    TextView t;
    ArrayList<TextView> array = new ArrayList<>();

    int[][] arr = new int[36][36];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_la);
        t = findViewById(R.id.vv);
        area = findViewById(R.id.area);
        for (int i=0; i <30;i++){
            for (int j=0; j <30;j++){
                arr[i][j]=0;
            }
        }
        ColorChangeHelper color = new ColorChangeHelper(findViewById(R.id.paret));

        for (int j = 0; j < 30; j++) {
            LinearLayout layout = new LinearLayout(La.this);
            LinearLayout.LayoutParams Layparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(Layparams);

            for (int i = 0; i < 30; i++) {
                TextView item = new TextView(La.this);
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

                //new WebView(this).loadUrl("http://192.168.0.9:8080/A/" + (y < 10 ? "0" + y : y) + "" + (x < 10 ? "0" + x : x) + "" + color.now_color);
                class f extends  Thread{
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://192.168.0.75:8080/A/" + (y < 10 ? "0" + y : y) + "" + (x < 10 ? "0" + x : x) + "" + color.now_color);
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
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
                new f().start();
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
                URL url = new URL("http://192.168.0.75:8080/A");
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
        final String[] data = readData(in)
                .replace("<h1>Las World!</h1>", "")
                .replace(" ", "=")
                .replace("[", "")
                .replace("]", "")
                .replace("\n","")
                .split("=");

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                //t.setText(data[0]);
                for(int i=0; i < 900; i++){
                    array.get(i).setBackgroundColor(Integer.parseInt(data[i])==0?-1:Integer.parseInt(data[i]));
                }

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
