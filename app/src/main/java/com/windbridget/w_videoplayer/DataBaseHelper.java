package com.windbridget.w_videoplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ru on 11/17/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
//    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String TABLE_CREATE = "CREATE TABLE contacts (id INTEGER primary key not null, " +
            "name TEXT not null, username TEXT not null, password TEXT not null);";
    SQLiteDatabase db;
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertContact(Contact contact){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM contacts";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME,contact.getName());
//        values.put(COLUMN_EMAIL,contact.getEmail());
        values.put(COLUMN_USERNAME,contact.getUsername());
        values.put(COLUMN_PASSWORD,contact.getPassword());
        String msg;
        db.insert(TABLE_NAME, null, values);
    }

    public String searchPassword(String username){
        db = this.getReadableDatabase();
        String query = "SELECT username, password FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                b = cursor.getString(1);
                if(a.equals(username)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

}
