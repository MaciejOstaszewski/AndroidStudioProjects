package com.example.start.l4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.start.l4.Adapters.DebtorsAdapter;
import com.example.start.l4.Database.DatabaseOpenHelper;
import com.example.start.l4.Models.Debtor;

import java.util.ArrayList;
import java.util.List;

public class DebtorsListActivity extends AppCompatActivity {

    private Button fromContactsButton;
    private Intent returnIntent;
    private Intent pickContactIntent;
    ListView debtorsList;
    DebtorsAdapter adapter;
    List<String> myStrings = new ArrayList<>();
    List<Debtor> debtors = new ArrayList<>();
    DatabaseOpenHelper dbHelper;
    SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debtors_list);
        dbHelper = new DatabaseOpenHelper(this);
        mDB = dbHelper.getWritableDatabase();

        debtorsList = (ListView) findViewById(R.id.debtorsList);
        adapter = new DebtorsAdapter(this, debtors);
        debtorsList.setAdapter(adapter);

        fromContactsButton = (Button) findViewById(R.id.fromContacts);
        fromContactsButton.setOnClickListener(fromContactsButtonListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Uri contactUri = data.getData();
                String[] projection = {
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Photo.PHOTO_URI

                };

                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME);
                int column2 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int column3 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI);

                int idx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI);
                String avatar = cursor.getString(idx);
                if (avatar != null) {
                    Log.d("LOGd", avatar);
                    Log.d("LOGd", cursor.getString(column3));
                }
                dbHelper.addDebtor(new Debtor(
                        cursor.getString(column2),
                        cursor.getString(column),
                        avatar
                        ));
                debtors = dbHelper.getAllDebtors();
                for (Debtor d : debtors){
                    Log.d("LOGG", d.getName());
                }
                adapter.notifyDataSetChanged();

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
