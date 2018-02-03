package com.example.start.l13;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evernote.android.job.JobManager;
import com.example.start.l13.TMDataManager.IValueService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button getValuesButton;
    Button eventButton;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = (TextView) findViewById(R.id.values);

        button = (Button) findViewById(R.id.jobButton);
        button.setOnClickListener(jobListener);
        getValuesButton = (Button) findViewById(R.id.getStaticValuesButton);
        getValuesButton.setOnClickListener(getStaticValuesListener);

        eventButton = (Button) findViewById(R.id.eventButton);
        eventButton.setOnClickListener(eventListener);


        //manifest localization access request
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity)
                            getApplicationContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

    }

    View.OnClickListener eventListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), EventActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener jobListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            JobManager.create(getApplicationContext()).addJobCreator(new DemoJobCreator());
            ReportJob.scheduleJob();
        }
    };

    View.OnClickListener getStaticValuesListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apps.ii.uph.edu.pl:88/TMDataManager/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IValueService service = retrofit.create(IValueService.class);

            Call<List<String>> values = service.listValues();

            callBack(values);
        }

        void callBack(Call<List<String>> values) {
            values.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>>
                        response) {
                    String elements = " ";
                    for (String el : response.body()) {
                        elements += el + " ";
                    }
                    results.setText(elements); //results – TextView
                    Log.d("WebApi", elements);
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Log.d("WebApi", t.getMessage());
                }
            });
        }


    };


}
