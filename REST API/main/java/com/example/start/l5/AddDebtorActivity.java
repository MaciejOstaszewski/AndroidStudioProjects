package com.example.start.l5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDebtorActivity extends AppCompatActivity {

    private EditText debtorName;
    private EditText debtorPhone;
    private Button addDebtorButton;
    private Intent returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debtor);


        debtorName = (EditText) findViewById(R.id.nameEdit);
        debtorPhone = (EditText) findViewById(R.id.phoneEdit);
        addDebtorButton = (Button) findViewById(R.id.addButton);

        addDebtorButton.setOnClickListener(addButtonListener);


    }
    public View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            returnIntent = getIntent();
            returnIntent.putExtra("Name", debtorName.getText());
            returnIntent.putExtra("Phone", debtorPhone.getText());
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    };

}
