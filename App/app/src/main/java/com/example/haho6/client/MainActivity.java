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
    LinearLayout area;
    Handler handler = new Handler();
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.vv);
        area = findViewById(R.id.area);
        ArrayList<TextView> array = new ArrayList<>();
        ArrayList<Button> Buttons = new ArrayList<>();

        ColorChangeHelper color = new ColorChangeHelper(findViewById(R.id.paret));

        for(int j =0; j< 30;j++) {
            LinearLayout layout = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams Layparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(Layparams);

            for(int i =0; i< 30;i++) {
                TextView item = new TextView(MainActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(2,2,2,2);
                item.setLayoutParams(params);
                item.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item.setTextSize(0);
                item.setPadding(13,13,13,10);
                layout.addView(item);
                array.add(item);
            }
            area.addView(layout);
        }
        NetThread netThread = new NetThread();
        netThread.url_ = "http://localhost:8080/A/001111";
        netThread.run();
        findViewById(R.id.in).setOnTouchListener((view, event)->{
            if(event.getAction()== MotionEvent.ACTION_MOVE){
                //색변경
                //인터넷에 전달
                //
                new GetServer().execute();
                t.setText(((int)event.getX()-(int)area.getX())/30+" ::  "+((int)event.getY()-(int)area.getY())/30+":");
                int x =((int)event.getX()-(int)area.getX())/30;
                int y =((int)event.getY()-(int)area.getY())/30;
                if(x<0||x>=30||y<0||y>=30)
                    return false;
                array.get(y*30+x).setBackgroundColor(color.color);
                return true;
            }
            if(event.getAction()== MotionEvent.ACTION_DOWN){
                t.setText(event.getX()+" ::  "+event.getY());
                return true;
            }
            return false;
        });
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