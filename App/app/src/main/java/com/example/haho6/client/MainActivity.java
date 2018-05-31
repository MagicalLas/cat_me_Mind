package com.example.haho6.client;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        area = findViewById(R.id.area);
        for(int j =0; j< 30;j++) {
            LinearLayout layout = new LinearLayout(MainActivity.this);
            LinearLayout.LayoutParams Layparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(Layparams);

            for(int i =0; i< 30;i++) {
                TextView item = new TextView(MainActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(1,1,1,1);
                item.setLayoutParams(params);
                item.setBackgroundColor(Color.parseColor("#FF7200"));
                item.setTextColor(Color.parseColor("#FF7200"));
                item.setTextSize(0);
                item.setPadding(20,20,20,20);
                item.setText("");
                item.setOnClickListener((v)->{
                    //v.setBackgroundColor(Color.parseColor("#007200"));
                });
                item.setOnTouchListener((vi,motionEvent)->{
                    vi.setBackgroundColor(Color.parseColor("#007200"));
                    return true;
                });
                item.setOnDragListener((view, dragEvent)->{
                    view.setBackgroundColor(Color.parseColor("#007200"));
                    return false;
                });
                layout.addView(item);
            }
            area.addView(layout);
        }
    }
}
