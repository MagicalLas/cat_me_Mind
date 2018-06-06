package com.example.haho6.client;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

public class ColorChangeHelper implements View.OnClickListener{

    int color;
    ColorChangeHelper(LinearLayout a)
    {
        color= Color.DKGRAY;
        for (int i=1;i<a.getChildCount(); i++){
            a.getChildAt(i).setOnClickListener(this::onClick);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.black){

            color= Color.BLACK;
        }
        if(v.getId()==R.id.blue){

            color= Color.BLUE;
        }
        if(v.getId()==R.id.sky){

            color= Color.CYAN;
        }
        if(v.getId()==R.id.pink){

            color= Color.MAGENTA;
        }
        if(v.getId()==R.id.yellow){

            color= Color.YELLOW;
        }
        if(v.getId()==R.id.red){

            color= Color.RED;
        }
        if(v.getId()==R.id.green){

            color= Color.GREEN;
        }
    }
}
