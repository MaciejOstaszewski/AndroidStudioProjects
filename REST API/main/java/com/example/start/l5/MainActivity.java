package com.example.start.l5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.start.l5.Interfaces.MyJsonResponseListener;
import com.example.start.l5.Tasks.LoadImageTask;
import com.example.start.l5.Tasks.LoadJsonTask;

public class MainActivity extends AppCompatActivity {

    private Button helloButton;
    private Button getTextButton;
    private Button getImageButton;
    private Button startButton;
    private ProgressBar downloadProgressBar;
    private ImageView image;
    private TextView text;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloButton = (Button) findViewById(R.id.helloButton);
        getTextButton = (Button) findViewById(R.id.getTextButton);
        getImageButton = (Button) findViewById(R.id.getImageButton);
        startButton = (Button) findViewById(R.id.startButton);
        downloadProgressBar = (ProgressBar) findViewById(R.id.downloadProgressBar);
        image = (ImageView) findViewById(R.id.downloadedImage);
        text = (TextView) findViewById(R.id.helloText);

        helloButton.setOnClickListener(helloButtonListener);
        getTextButton.setOnClickListener(getTextListener);
        getImageButton.setOnClickListener(getImageListener);
        startButton.setOnClickListener(startButtonListener);
        intent = new Intent(this, LoginActivity.class);
    }

    public View.OnClickListener getTextListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LoadJsonTask loadJsonTask = new LoadJsonTask(new MyJsonResponseListener() {
                @Override
                public void onJsonResponseChange(String json) {
                    text.setText(json);
                }
            });
            loadJsonTask.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/Hello");
        }
    };

    public View.OnClickListener getImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            downloadProgressBar.setVisibility(View.VISIBLE);
            LoadImageTask loadImageTask = new LoadImageTask(downloadProgressBar, image);
            loadImageTask.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/AndroidImage");
        }
    };

    public View.OnClickListener helloButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), getText(R.string.helloText), Toast.LENGTH_SHORT).show();
        }
    };

    public View.OnClickListener startButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(intent);
        }
    };
}
