package com.example.start.l4;

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

public class MainActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private Button signButton;
    private CheckBox saveCheckBox;
    private SharedPreferences preferences;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login = (EditText) findViewById(R.id.loginEdit);
        password = (EditText) findViewById(R.id.passwordEdit);
        signButton = (Button) findViewById(R.id.sign_button);
        saveCheckBox = (CheckBox) findViewById(R.id.checkBox);

        signButton.setOnClickListener(signButtonListener);

        preferences = this.getPreferences(MODE_PRIVATE);
        login.setText(preferences.getString("login", ""));
        password.setText(preferences.getString("password", ""));
        intent = new Intent(this, DebtorsListActivity.class);
    }

    private View.OnClickListener signButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (valid()) {
                Log.d("LOG", "pyrka");
                if (getCheckBoxState()) {
                    saveLoginData();
                }
                startActivity(intent);

            } else {
                Log.d("LOG", "kartofelek");
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
