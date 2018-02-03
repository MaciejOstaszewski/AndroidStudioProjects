package com.example.start.l13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.start.l13.TMDataManager.IEventService;
import com.example.start.l13.TMDataManager.IValueService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventActivity extends AppCompatActivity {

    Button getInfoButton;
    Button getInfoIdButton;
    Button postInfoButton;
    Button putButton;
    Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getInfoButton = (Button) findViewById(R.id.getInfoButton);
        getInfoIdButton = (Button) findViewById(R.id.getInfoIdButton);
        postInfoButton = (Button) findViewById(R.id.postInfoButton);
        putButton = (Button) findViewById(R.id.putButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);


        getInfoButton.setOnClickListener(getInfoListener);
        getInfoIdButton.setOnClickListener(getInfoIdListener);
        postInfoButton.setOnClickListener(postInfoListener);
        putButton.setOnClickListener(putListener);
        deleteButton.setOnClickListener(deleteListener);


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
//                results.setText(elements); //results – TextView
                Log.d("WebApi", elements);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("WebApi", "FAILURE: "+t.getMessage());
            }
        });
    }


    View.OnClickListener getInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apps.ii.uph.edu.pl:88/TMDataManager/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IEventService service = retrofit.create(IEventService.class);

            Call<List<String>> values = service.eventInfo();
            callBack(values);
        }
    };

    View.OnClickListener getInfoIdListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apps.ii.uph.edu.pl:88/TMDataManager/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IEventService service = retrofit.create(IEventService.class);

            Call<List<String>> values = service.eventInfo(1);
            callBack(values);
        }
    };

    View.OnClickListener postInfoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apps.ii.uph.edu.pl:88/TMDataManager/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IEventService service = retrofit.create(IEventService.class);

            Call<List<String>> values = service.eventInfoPost();
            callBack(values);
        }
    };

    View.OnClickListener putListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apps.ii.uph.edu.pl:88/TMDataManager/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IEventService service = retrofit.create(IEventService.class);

            Call<List<String>> values = service.eventPut(1);
        }
    };

    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://apps.ii.uph.edu.pl:88/TMDataManager/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            IEventService service = retrofit.create(IEventService.class);

            Call<List<String>> values = service.eventDelete(1);
        }
    };

}
