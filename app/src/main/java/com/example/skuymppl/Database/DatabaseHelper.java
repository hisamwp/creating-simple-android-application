package com.example.skuymppl.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.skuymppl.Database.model.Login;
import com.example.skuymppl.Database.model.User;

import java.sql.Blob;

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
        db.execSQL(User.CREATE_TABLE);
        //db.execSQL(Login.CREATE_TABLE);
        //Log.d("TERBUAT", "onClick: ");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        //db.execSQL("DROP TABLE IF EXISTS " + Login.TABLE_LOGIN);

        // Create tables again
        onCreate(db);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,// Selecting Table
                new String[]{User.COLUMN_ID, User.COLUMN_EMAIL, User.COLUMN_PASS},//Selecting columns want to query
                User.COLUMN_EMAIL + "=?",
                new String[]{user.getEmail()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(1), cursor.getString(2));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public Boolean cekEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(User.TABLE_NAME,// Selecting Table
                new String[]{User.COLUMN_ID, User.COLUMN_EMAIL, User.COLUMN_PASS},//Selecting columns want to query
                User.COLUMN_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then true
            return true;

            } else {

            return false;
        }
    }


    public void insertUser(String nama, String email, String password, String alamat, String notelp, String noktp, byte[] ktp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAMA, nama);
        values.put(User.COLUMN_EMAIL, email);
        values.put(User.COLUMN_PASS, password);
        values.put(User.COLUMN_ALAMAT, alamat);
        values.put(User.COLUMN_NOTELP, notelp);
        values.put(User.COLUMN_NOKTP, noktp);
        values.put(User.COLUMN_KTP, ktp);

        long id = db.insert(User.TABLE_NAME, null, values);

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
