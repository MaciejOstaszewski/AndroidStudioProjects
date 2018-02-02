package com.example.start.l8;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ActivityTwo extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout rel = new RelativeLayout(this);
        image = new ImageView(this);
        image.setImageResource(R.drawable.jellybeans);
        int height = (int) getResources().getDimension(R.dimen.jelly_height);
        int width = (int) getResources().getDimension(R.dimen.jelly_width);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);

        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        image.setLayoutParams(params);

        rel.addView(image);

        setContentView(rel);
    }
}
