package com.example.start.l8;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ActivityFour extends AppCompatActivity {

    ImageView right;
    ImageView left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout relativeLayout = new RelativeLayout(this);

        right = new ImageView(this);
        left = new ImageView(this);

        int height = (int) getResources().getDimension(R.dimen.image_right_height);
        int width = (int) getResources().getDimension(R.dimen.image_left_width);

        ShapeDrawable redShape = new ShapeDrawable(new OvalShape());
        redShape.getPaint().setColor(Color.RED);
        redShape.setIntrinsicHeight(height);
        redShape.setIntrinsicWidth(width);
        redShape.setAlpha(64);
        ShapeDrawable blueShape = new ShapeDrawable(new OvalShape());
        blueShape.getPaint().setColor(Color.GREEN);
        blueShape.setIntrinsicHeight(height);
        blueShape.setIntrinsicWidth(width);
        blueShape.setAlpha(64);

        int padding = (int) getResources().getDimension(R.dimen.image_right_padding) /2;

        right.setPadding(padding, padding, padding, padding);
        left.setPadding(padding, padding, padding, padding);
        right.setImageDrawable(redShape);
        left.setImageDrawable(blueShape);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        left.setLayoutParams(params);

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(width, height);
        params2.addRule(RelativeLayout.CENTER_VERTICAL);
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        right.setLayoutParams(params2);

        relativeLayout.addView(right);
        relativeLayout.addView(left);


        setContentView(relativeLayout);
    }
}
