package com.mwcd.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ARCHANA on 23-Mar-18.
 */

public class DatabaseMSWD extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "mswd";

    private static final String TABLE_NAME = "register";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FNAME = "fname";
    private static final String COLUMN_LNAME = "lname";
    private static final String COLUMN_TIMESTAMP = "timeRegistered";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phoneNumber";
    private static final String COLUMN_AADHAR = "aadhar";
    private static final String COLUMN_CATEGORIES = "categories";
    private static final String COLUMN_USERNAME = "userName";


    public DatabaseMSWD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_FNAME + " TEXT,"+ COLUMN_LNAME + " TEXT,"+COLUMN_PASSWORD +" TEXT,"
                        +COLUMN_AADHAR+ " TEXT UNIQUE,"+COLUMN_PHONE+ " TEXT UNIQUE,"
                        +COLUMN_CATEGORIES+ " TEXT,"+COLUMN_USERNAME +" TEXT UNIQUE,"
                        + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_FNAME + " TEXT,"+ COLUMN_LNAME + " TEXT,"+COLUMN_PASSWORD +" TEXT,"
                        +COLUMN_AADHAR+ " TEXT UNIQUE,"+COLUMN_PHONE+ " TEXT UNIQUE,"
                        +COLUMN_CATEGORIES+ " TEXT,"+COLUMN_USERNAME +" TEXT UNIQUE,"
                        + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ")";
         sqLiteDatabase.execSQL(CREATE_TABLE);
    }


    public long insertUser(Register note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FNAME, note.getfName());
        values.put(COLUMN_LNAME, note.getlName());
        values.put(COLUMN_USERNAME, note.getUserName());
        values.put(COLUMN_PASSWORD, note.getPassword());
        values.put(COLUMN_AADHAR, note.getAadharNumber());
        values.put(COLUMN_PHONE, note.getPhoneNumber());
        values.put(COLUMN_CATEGORIES, note.getCategories());
        long id = db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Register getData(){
        Register register = new Register();
        SQLiteDatabase database  = getReadableDatabase();
        String query = "Select * from "+TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        String[] data = null;
        if (cursor.moveToFirst()) {
            do {
              register.setfName(cursor.getString(cursor.getColumnIndex(COLUMN_FNAME)));
              register.setlName(cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)));
              register.setUserName(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
              register.setAadharNumber(cursor.getString(cursor.getColumnIndex(COLUMN_AADHAR)));
              register.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));

            } while (cursor.moveToNext());
        }
        database.close();
        return register;
    }
}
