package com.example.start.l7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.sensorsButton);
        button.setOnClickListener(sensorListener);

    }


    View.OnClickListener sensorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(view.getContext(), SensorActivity.class);
            startActivity(intent);
        }
    };
}
