package com.example.shubhama003.memonew;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by shubhama003 on 6/7/17.
 */

public class adapter extends SimpleCursorAdapter {
    int p;
    int coloursy[];
    public adapter(Context context, int layout, Cursor c, String[] from, int[] to,int[] polor) {

        super(context, layout, c, from, to,0);
        p=0;
        coloursy = polor;
    }

   @Override
    public void bindView(View view, Context context, Cursor cursor) {
        p=p+1;
        if(p%5==0)
        view.setBackgroundColor(Color.parseColor("#C8E6C9"));
        else
            if(p%5==1)
                view.setBackgroundColor(Color.parseColor("#FFCDD2"));
            else
                if(p%5==2)
                    view.setBackgroundColor(Color.parseColor("#E1BEE7"));
                else
                    if(p%5==3)
                        view.setBackgroundColor(Color.parseColor("#BBDEFB"));
                    else
                        view.setBackgroundColor(Color.parseColor("#FFE0B2"));
        super.bindView(view, context, cursor);

    }

}
