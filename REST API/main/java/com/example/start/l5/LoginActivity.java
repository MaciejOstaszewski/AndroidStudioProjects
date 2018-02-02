package com.example.start.l5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.start.l5.Interfaces.MyJsonResponseListener;
import com.example.start.l5.Tasks.LoadLoginJsonTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private Button signButton;
    private CheckBox saveCheckBox;
    private SharedPreferences preferences;
    private Intent intent;
    SharedPreferences applicationPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (EditText) findViewById(R.id.loginEdit);
        password = (EditText) findViewById(R.id.passwordEdit);
        signButton = (Button) findViewById(R.id.sign_button);
        saveCheckBox = (CheckBox) findViewById(R.id.checkBox);

        signButton.setOnClickListener(signButtonListener);

        preferences = this.getPreferences(MODE_PRIVATE);
        login.setText(preferences.getString("login", ""));
        password.setText(preferences.getString("password", ""));
        applicationPreferences = getSharedPreferences("L5", MODE_PRIVATE);
        intent = new Intent(this, DebtorsListActivity.class);
    }

    private View.OnClickListener signButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            if (valid()) {

                if (getCheckBoxState()) {
                    saveLoginData();
                }
//                startActivity(intent);
                LoadLoginJsonTask loadLoginJsonTask = new LoadLoginJsonTask(new MyJsonResponseListener() {
                    @Override
                    public void onJsonResponseChange(String string) {
                        try {
                            JSONObject jsonObject = new JSONObject(string);

                            String result = jsonObject.getString("response");
                            String token = jsonObject.getString("token");

                            if (result.equals("success")) {

                                Toast.makeText(view.getContext(), "Access Granted", Toast.LENGTH_SHORT).show();
                                applicationPreferences.edit().putString("token", token).apply();
                                startActivity(intent);

                            } else {
                                Toast.makeText(view.getContext(), "Access Denied", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                String loginStr = String.valueOf(login.getText());
                String passwdStr = String.valueOf(password.getText());

                try {
                    loadLoginJsonTask.execute("http://apps.ii.uph.edu.pl:88/MSK/MSK/Authenticate"
                            , "login=" + URLEncoder.encode(loginStr, "UTF-8")
                            , "password=" + URLEncoder.encode(passwdStr, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
            }

        }
    };


    public void saveLoginData() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();

        preferencesEditor.putString("login", login.getText().toString());
        preferencesEditor.putString("password", password.getText().toString());


        if (!preferencesEditor.commit())
            Toast.makeText(this, getText(R.string.login_data_save_error), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, getText(R.string.login_data_save_confirm), Toast.LENGTH_SHORT).show();
    }


    public Boolean getCheckBoxState() {
        return saveCheckBox.isChecked();
    }

    public Boolean valid() {
        Boolean validOK = true;
        if (String.valueOf(login.getText()).isEmpty()) {
            login.setError(getString(R.string.login_empty_error));
            validOK = false;
        }
        if (String.valueOf(password.getText()).isEmpty()) {
            password.setError(getString(R.string.password_empty_error));
            validOK = false;
        }
        return validOK;
    }
}
