package com.example.ticktacktoe2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class SquareButton extends android.support.v7.widget.AppCompatButton {
    public SquareButton(Context context){
        super(context);
    }

    public SquareButton(Context c, AttributeSet a){
        super(c, a);
    }

    public SquareButton(Context c, AttributeSet a, int defStyle){
        super(c, a, defStyle);
    }

    @Override
    protected void onMeasure(int width, int heigth){
        super.onMeasure(width, width);
    }

}
