package com.example.haho6.client;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Touch extends View{

    TextView t;
    public Touch(Context context) {
        super(context);
    }
    public boolean onTouchListener(MotionEvent event){

        t = findViewById(R.id.vv);
        if(event.getAction()==MotionEvent.ACTION_DOWN){

            t.setText(event.getX()+" ::  "+event.getY());
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE){

            t.setText(event.getX()+" ::  "+event.getY());
        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
