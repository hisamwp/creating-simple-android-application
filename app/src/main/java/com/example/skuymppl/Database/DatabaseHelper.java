package com.example.skuymppl.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.skuymppl.Database.model.Login;
import com.example.skuymppl.Database.model.User;

public class DatabaseHelper extends SQLiteOpenHelper{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "FPMIS";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        //db.execSQL(User.CREATE_TABLE);
        db.execSQL(Login.CREATE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Login.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public String selectUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_NAMA},
                User.COLUMN_EMAIL + "=?",
                new String[]{String.valueOf(email)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        String namaUser = cursor.getString(cursor.getColumnIndex(User.COLUMN_NAMA));

        // close the db connection
        cursor.close();

        return namaUser;
    }

    public String selectPass(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Login.TABLE_NAME,
                new String[]{Login.COLUMN_PASS},
                Login.COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null, null);

        // prepare note object
        String passUser = cursor.getString(cursor.getColumnIndex(Login.COLUMN_PASS));

        // close the db connection
        cursor.close();

        return passUser;
    }

    public void insertUser(String nama, String email, String password, String alamat, String notelp, String noktp, String ktp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(User.COLUMN_NAMA, nama);
        values.put(Login.COLUMN_EMAIL, email);
        values.put(Login.COLUMN_PASS, password);
        //values.put(User.COLUMN_ALAMAT, alamat);
        //values.put(User.COLUMN_NOTELP, notelp);
        //values.put(User.COLUMN_NOKTP, noktp);
        //values.put(User.COLUMN_KTP, ktp);

        db.insert(Login.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
    }



    public long updateUser(String nama, String foto, String noktp, String alamat, String ktp, String sim) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAMA, nama);
        values.put(User.COLUMN_FOTO, foto);
        values.put(User.COLUMN_NOKTP, noktp);
        values.put(User.COLUMN_ALAMAT, alamat);
        values.put(User.COLUMN_KTP, ktp);
        values.put(User.COLUMN_SIM, sim);

        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


}
