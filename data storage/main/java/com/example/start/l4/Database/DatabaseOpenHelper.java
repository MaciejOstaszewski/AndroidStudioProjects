package com.example.start.l4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.start.l4.Models.Debtor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by start on 2017-11-10.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private Context mContext;
    final static String _ID = "_id";
    final static String DEBTOR_NAME = "name";
    final static String DEBTOR_PHONE = "phone";
    final static String DEBTOR_PHOTO_URI = "photo_uri";
    final static String TABLE_NAME = "debtors";
    final static String[] columns = {_ID, DEBTOR_NAME, DEBTOR_PHONE,
            DEBTOR_PHOTO_URI};
    final private static String NAME = "debtors_db";
    final private static Integer VERSION = 1;
    final private static String CREATE_CMD =
            "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DEBTOR_NAME + " TEXT NOT NULL, "
                    + DEBTOR_PHONE + " TEXT NOT NULL, "
                    + DEBTOR_PHOTO_URI + " TEXT );";

    public DatabaseOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }

    // CRUD

    public void addDebtor(Debtor debtor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DEBTOR_NAME, debtor.getName());
        values.put(DEBTOR_PHONE, debtor.getPhoneNumber());
        values.put(DEBTOR_PHOTO_URI, debtor.getPhoto());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Debtor getDebtor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        _ID, DEBTOR_NAME, DEBTOR_PHONE, DEBTOR_PHOTO_URI}, _ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Debtor debtor = new Debtor(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        return debtor;
    }


    public List<Debtor> getAllDebtors() {
        List<Debtor> debtors = new ArrayList<Debtor>();

//        String selectQuery = "SELECT * FROM" + TABLE_NAME;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//
//        if (cursor.moveToFirst()) {
//            do {
//                Debtor debtor = new Debtor();
//                debtor.setId(Integer.parseInt(cursor.getString(0)));
//                debtor.setName(cursor.getString(1));
//                debtor.setPhoneNumber(cursor.getString(2));
//                debtor.setPhoto(cursor.getString(3));
//
//                debtors.add(debtor);
//
//            } while (cursor.moveToNext());
//
//        }
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, _ID);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Debtor debtor = new Debtor();

            int idx = cursor.getColumnIndex(DEBTOR_NAME);
            debtor.setName(cursor.getString(idx));

            idx = cursor.getColumnIndex(DEBTOR_PHONE);
            debtor.setPhoneNumber(cursor.getString(idx));

            idx = cursor.getColumnIndex(DEBTOR_PHOTO_URI);
            debtor.setPhoto(cursor.getString(idx));


            idx = cursor.getColumnIndex(_ID);
            debtor.setId(cursor.getInt(idx));

            debtors.add(debtor);

            cursor.moveToNext();
        }

        cursor.close();
        return debtors;
    }
}