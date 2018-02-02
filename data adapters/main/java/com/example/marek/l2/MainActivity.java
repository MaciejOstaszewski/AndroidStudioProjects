package com.example.marek.l2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<String> myStrings = new ArrayList<>();
    int index = 0;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterRemove;
    boolean removeMode = true;
    boolean listStatus = true;
    ListView listView;
    Button addBtn;
    Button rmvBtn;
    List<String> checked = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            Log.d("log", "SavedInstance != null");
            myStrings = savedInstanceState.getStringArrayList("TestStrings");
            checked = savedInstanceState.getStringArrayList("checked");
            index = savedInstanceState.getInt("Index");
            listStatus = savedInstanceState.getBoolean("listStatus");
            removeMode = savedInstanceState.getBoolean("removeMode");

        }
        Log.d("log", "create");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("log", "start");
        addBtn = (Button) findViewById(R.id.ADDbutton);
        addBtn.setOnClickListener(addListener);
        rmvBtn = (Button) findViewById(R.id.button2);
        rmvBtn.setOnClickListener(removeListener);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStrings);
        adapterRemove = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, myStrings);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        if (listStatus) {
            listView.setAdapter(adapter);
            addBtn.setText("ADD");
            addBtn.setOnClickListener(addListener);
            rmvBtn.setText("REMOVE");
            listView.setOnItemClickListener(mMessageClickedHandler2);
        } else {
            listView.setAdapter(adapterRemove);
            addBtn.setText("DELETE");
            addBtn.setOnClickListener(deleteListener);
            rmvBtn.setText("CANCEL");
            listView.setOnItemClickListener(mMessageClickedHandler);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Log.d("log", "OnSaveInstanceState");


        // Always call the superclass so it can save the view hierarchy state
        savedInstanceState.putStringArrayList("TestStrings", (ArrayList<String>) myStrings);
        savedInstanceState.putStringArrayList("checked", (ArrayList<String>) checked);
        savedInstanceState.putInt("Index", index);
        savedInstanceState.putBoolean("removeMode", removeMode);

        if (listStatus) {
            savedInstanceState.putBoolean("listStatus", listStatus);
        } else {
            savedInstanceState.putBoolean("listStatus", listStatus);
        }

        super.onSaveInstanceState(savedInstanceState);
    }

    private View.OnClickListener removeListener = new View.OnClickListener() {
        public void onClick(View view) {


            if (removeMode) {
                listView.setAdapter(adapterRemove);
                addBtn.setText("DELETE");
                addBtn.setOnClickListener(deleteListener);
                rmvBtn.setText("CANCEL");
                listStatus = false;
                listView.setOnItemClickListener(mMessageClickedHandler);

            } else {
                listView.setAdapter(adapter);
                addBtn.setText("ADD");
                addBtn.setOnClickListener(addListener);
                rmvBtn.setText("REMOVE");
                listStatus = true;
                listView.setOnItemClickListener(mMessageClickedHandler2);

            }
            removeMode = !removeMode;

        }
    };


    private View.OnClickListener addListener = new View.OnClickListener() {
        public void onClick(View view) {
            myStrings.add("item" + index++);
            adapter.notifyDataSetChanged();
        }
    };

    private View.OnClickListener deleteListener = new View.OnClickListener() {
        public void onClick(View view) {

            for (int i = 0; i < checked.size(); i++) {
                adapterRemove.remove(checked.get(i));
            }

            checked = new ArrayList<>();

            adapterRemove.notifyDataSetChanged();
            listView.setAdapter(adapter);
            addBtn.setText("ADD");
            addBtn.setOnClickListener(addListener);
            rmvBtn.setText("REMOVE");
            rmvBtn.setOnClickListener(removeListener);
            listView.setOnItemClickListener(mMessageClickedHandler2);
            removeMode = !removeMode;

        }
    };


    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(android.R.id.text1);
            checkedTextView.setChecked(!checkedTextView.isChecked());

            checked.add(adapterRemove.getItem(position));


        }




    };

    private AdapterView.OnItemClickListener mMessageClickedHandler2 = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            Log.d("LOG", "PYRKA");
        }
    };


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("log", "restart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("log", "resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("log", "pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("log", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("log", "destroy");
    }
}
