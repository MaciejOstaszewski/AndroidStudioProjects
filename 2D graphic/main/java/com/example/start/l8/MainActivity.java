package com.example.start.l8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);


        button.setOnClickListener(listener1);
        button2.setOnClickListener(listener2);
        button3.setOnClickListener(listener3);
        button4.setOnClickListener(listener4);
        button5.setOnClickListener(listener5);
        button6.setOnClickListener(listener6);

    }


    View.OnClickListener listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(getApplicationContext(), ActivityOne.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(getApplicationContext(), ActivityTwo.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(getApplicationContext(), ActivityThree.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(getApplicationContext(), ActivityFour.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener5 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(getApplicationContext(), ActivityFive.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener6 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(getApplicationContext(), ActivitySix.class);
            startActivity(intent);
        }
    };
}
