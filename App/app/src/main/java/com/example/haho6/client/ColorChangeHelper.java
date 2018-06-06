package com.example.haho6.client;

import android.graphics.Color;
import android.view.View;

public class ColorChangeHelper implements View.OnClickListener{

    int color;
    ColorChangeHelper()
    {
        color= Color.DKGRAY;
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
        if(v.getId()==R.id.gray){

            color= Color.GRAY;
        }
    }
}
