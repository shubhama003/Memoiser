package com.example.shubhama003.memonew;


import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class About extends AppCompatActivity {
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        t1 =(TextView) findViewById(R.id.textView2);
        t2 =(TextView) findViewById(R.id.textView3);
        Typeface ty = Typeface.createFromAsset(getAssets(),"fonts/ps.otf");
        t1.setTypeface(ty);
        ActionBar bar = getActionBar();
        //for color
        //bar.setBackgroundColor(Color.parseColor("#FFE0B2"));

    }
}
