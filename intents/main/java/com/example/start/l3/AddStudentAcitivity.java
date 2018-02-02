package com.example.start.l3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddStudentAcitivity extends AppCompatActivity {

    private TextView firstName;
    private TextView phoneNumber;
    private Button addButton;
    private Button fromContactsButton;
    private Intent returnIntent;
    private Intent pickContactIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_acitivity);

        firstName = (TextView) findViewById(R.id.StudentName);
        phoneNumber = (TextView) findViewById(R.id.StudentPhone);
        addButton = (Button) findViewById(R.id.ADDStudent);
        addButton.setOnClickListener(addButtonListener);
        fromContactsButton = (Button) findViewById(R.id.contacts);
        fromContactsButton.setOnClickListener(fromContactsButtonListener);


    }

    public View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            returnIntent = getIntent();
            returnIntent.putExtra("Name", firstName.getText());
            returnIntent.putExtra("Phone", phoneNumber.getText());
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Uri contactUri = data.getData();
                String[] projection = {
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                };
                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME);
                String displayName = cursor.getString(column);
                int column2 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                firstName.setText(displayName);
                displayName = cursor.getString(column2);
                phoneNumber.setText(displayName);

            }
        }
    }

    public View.OnClickListener fromContactsButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
            pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(pickContactIntent, 2);
        }
    };
}
