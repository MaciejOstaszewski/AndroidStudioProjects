package com.example.start.l5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.start.l5.Interfaces.MyJsonResponseListener;
import com.example.start.l5.Tasks.LoadDebtorTask;
import com.example.start.l5.Tasks.LoadJsonTask;
import com.example.start.l5.Tasks.LoadLoginJsonTask;
import com.example.start.l5.adapters.DebtorsAdapter;
import com.example.start.l5.models.Debtor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DebtorsListActivity extends AppCompatActivity {

    private Button addDebtorButton;
    private Button deleteButton;
    private Intent addDebtorIntent;
    private ListView debtorsList;
    private DebtorsAdapter adapter;
    private List<Debtor> debtors;

    private String debtorName;
    private String debtorPhone;
    private int id;
    private String token;

    private Boolean deleteMode = false;
    SharedPreferences applicationPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtors_list);
        addDebtorIntent = new Intent(this, AddDebtorActivity.class);
        debtors = new ArrayList<>();
        debtorsList = (ListView) findViewById(R.id.debtorsList);
        adapter = new DebtorsAdapter(this, debtors);
        debtorsList.setAdapter(adapter);
        debtorsList.setOnItemClickListener(mMessageClickedHandler);


        addDebtorButton = (Button) findViewById(R.id.addDebtor);
        addDebtorButton.setOnClickListener(addDebtorListener);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(deleteListener);
        deleteButton.setText("Delete Mode");

        applicationPreferences = getSharedPreferences("L5", MODE_PRIVATE);
        token = applicationPreferences.getString("token", "");

        getDebtorsList();

    }


    private AdapterView.OnItemClickListener deleteItemListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View view, int position, long listId) {
            LoadLoginJsonTask loadLoginJsonTask = new LoadLoginJsonTask(new MyJsonResponseListener() {
                @Override
                public void onJsonResponseChange(String string) {
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        if (jsonObject.getString("response").equals("success")) {
                            showToast("Item deleted");
                            refresh();
                        } else {
                            showToast("Error");
                        }
                    } catch (JSONException e) {

                    }
                }
            });
            loadLoginJsonTask.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/DeleteDebtor"
                    , "token=" + token
                    , "dId=" + debtors.get(position).getId());


        }

    };

    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View view, int position, long listId) {



        }

    };


    public View.OnClickListener addDebtorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivityForResult(addDebtorIntent, 1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                CharSequence name = data.getCharSequenceExtra("Name");
                CharSequence phone = data.getCharSequenceExtra("Phone");

                debtorName = name.toString();
                debtorPhone = phone.toString();
                LoadJsonTask loadJsonTask = new LoadJsonTask(new MyJsonResponseListener() {
                    @Override
                    public void onJsonResponseChange(String string) {

                        if (string.equals("success")) {
                            showToast("Item added");
                            refresh();
                        } else {
                            showToast("Error");
                        }

                    }

                });

                loadJsonTask.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/AddDebtor?token="
                        + token
                        + "&dId=" + id
                        + "&dName=" + debtorName
                        + "&dPhone=" + debtorPhone);

            }


        }
        if (requestCode == Activity.RESULT_CANCELED) {

        }

    }

    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!deleteMode) {
                deleteButton.setText("Exit delete mode");
                showToast("Click on item to delete");
                debtorsList.setOnItemClickListener(deleteItemListener);
                deleteMode = true;
            } else {
                deleteButton.setText("Delete Mode");
                showToast("Exit delete mode");
                debtorsList.setOnItemClickListener(mMessageClickedHandler);
                deleteMode = false;
            }
        }
    };

    public void refresh(){
        resetDebtorList();
        getDebtorsList();
        adapter.notifyDataSetChanged();
    }


    public void resetDebtorList() {
        debtors = new ArrayList<>();
        debtorsList = (ListView) findViewById(R.id.debtorsList);
        adapter = new DebtorsAdapter(this, debtors);
        debtorsList.setAdapter(adapter);
    }

    public void getDebtorsList() {

        LoadDebtorTask loadDebtorTask = new LoadDebtorTask(new MyJsonResponseListener() {
            @Override
            public void onJsonResponseChange(String string) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    String responseType = jsonObject.getString("response");
                    if (responseType.equals("success")) {
                        JSONArray debtorsJsonArray = new JSONArray(jsonObject.getString("debtors"));
                        id = debtorsJsonArray.length();
                        for (int i = 0; i < debtorsJsonArray.length(); i++) {
                            JSONObject jsonArrayObj = debtorsJsonArray.getJSONObject(i);
                            debtors.add(new Debtor(
                                    Integer.parseInt(jsonArrayObj.getString("Id")),
                                    jsonArrayObj.getString("Name"),
                                    jsonArrayObj.getString("Phone"),
                                    jsonArrayObj.getString("Photo")
                            ));
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        loadDebtorTask.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/GetDebtors?token=" + token);

    }

    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
