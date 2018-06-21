package com.example.haho6.application;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

public class ColorChangeHelper implements View.OnClickListener{

    int now_color;
    ColorChangeHelper(LinearLayout a)
    {
        now_color= Color.DKGRAY;
        for (int i=1;i<a.getChildCount(); i++){
            a.getChildAt(i).setOnClickListener(this::onClick);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.blue){

            now_color= Color.BLUE;
        }
        if(v.getId()==R.id.sky){

            now_color= Color.CYAN;
        }
        if(v.getId()==R.id.pink){

            now_color= Color.MAGENTA;
        }
        if(v.getId()==R.id.yellow){

            now_color= Color.YELLOW;
        }
        if(v.getId()==R.id.red){

            now_color= Color.RED;
        }
        if(v.getId()==R.id.green){

            now_color= Color.GREEN;
        }
        if(v.getId()==R.id.LAP){
            now_color= Color.DKGRAY;
        }
    }
}

