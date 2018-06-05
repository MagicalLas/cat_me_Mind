package com.example.haho6.client;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout area;

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.vv);
        area = findViewById(R.id.area);
        ArrayList<TextView> array = new ArrayList<>();
        for(int j =0; j< 30;j++) {
            LinearLayout layout = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams Layparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(Layparams);

            for(int i =0; i< 30;i++) {
                TextView item = new TextView(MainActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,1,1,0);
                item.setLayoutParams(params);
                item.setBackgroundColor(Color.parseColor("#FF7200"));
                item.setTextColor(Color.parseColor("#FF7200"));
                item.setTextSize(0);
                item.setPadding(19,19,10,10);
                item.setText("");
                layout.addView(item);
                array.add(item);
            }
            area.addView(layout);
        }
        findViewById(R.id.in).setOnTouchListener((view, event)->{
            if(event.getAction()== MotionEvent.ACTION_MOVE){
                //색변경
                array.stream()
                        .filter(v->{
                            int x = (int) event.getX()/10;
                            int y = (int) event.getY()/10;
                            return false;})
                        .limit(1)
                        .forEach(i->{});
                //인터넷에 전달
                t.setText(event.getX()+" ::  "+event.getY()+" \n"+array.get(1).getX()+":"+array.get(0).getX()+" => "+area.getX());
                return true;
            }
            if(event.getAction()== MotionEvent.ACTION_DOWN){
                t.setText(event.getX()+" ::  "+event.getY());
                return true;
            }
            return false;
        });

    }
}