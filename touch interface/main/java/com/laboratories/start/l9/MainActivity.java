package com.laboratories.start.l9;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ImageView right;
    ImageView left;
    HashMap<Integer, ImageView> ovals = new HashMap<Integer, ImageView>();
    List<ImageView> freeImages = new ArrayList<>();
    List<ImageView> images = new ArrayList<ImageView>();
    ImageView ovalImage;
    ImageView ovalImage2;
    int height;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        right = new ImageView(this);
        left = new ImageView(this);

        ovalImage = new ImageView(this);
        ovalImage2 = new ImageView(this);
        height = (int) getResources().getDimension(R.dimen.shape_height);
        width = (int) getResources().getDimension(R.dimen.shape_width);
        ShapeDrawable redShape = new ShapeDrawable(new OvalShape());
        redShape.getPaint().setColor(Color.RED);
        redShape.setIntrinsicHeight(height);
        redShape.setIntrinsicWidth(width);
        redShape.setAlpha(125);

        ShapeDrawable greenShape = new ShapeDrawable(new OvalShape());
        greenShape.getPaint().setColor(Color.GREEN);
        greenShape.setIntrinsicHeight(height);
        greenShape.setIntrinsicWidth(width);
        greenShape.setAlpha(125);

        ovalImage.setImageDrawable(redShape);
        ovalImage2.setImageDrawable(greenShape);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(width, height);
        //params.addRule(RelativeLayout.CENTER_IN_PARENT);

        ovalImage.setLayoutParams(params);
        ovalImage2.setLayoutParams(params2);
        ovalImage.setVisibility(View.INVISIBLE);
        ovalImage2.setVisibility(View.INVISIBLE);

        relativeLayout.addView(ovalImage);
        relativeLayout.addView(ovalImage2);
        images.add(ovalImage);
        images.add(ovalImage2);

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN: {
                        int idx = event.getActionIndex();
                        int ID = event.getPointerId(idx);

                        if (images.size() > 0) {
                            ovals.put(ID, images.get(0));
                            images.remove(images.get(0));
                            ovals.get(ID).setVisibility(View.VISIBLE);
                        }



                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP: {
                        int idx = event.getActionIndex();
                        int ID = event.getPointerId(idx);
                        images.add(ovals.get(ID));
                        ovals.get(ID).setVisibility(View.INVISIBLE);
                        ovals.remove(ID);

                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        for (int idx = 0; idx < event.getPointerCount(); idx++) {
                            int ID = event.getPointerId(idx);
                            RelativeLayout.LayoutParams params =
                                    (RelativeLayout.LayoutParams) ovals.get(ID).getLayoutParams();
                            params.topMargin = (int) event.getY() - height / 2;//pobranie
                            // pozycji dla wskaźnika o konkretnym indeksie
                            params.leftMargin = (int) event.getX() - width / 2;
                            ovals.get(ID).setLayoutParams(params);//spowoduje ponowne narysowanie
                            // obiektu

                        }
                        break;
                    }
                }
                return true;
            }
        });
    }


}
