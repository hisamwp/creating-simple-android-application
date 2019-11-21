package com.example.skuymppl.Database.model;

public class Login {
    public static final String TABLE_LOGIN = "login";

    public static final String COLUMN_ID = "login_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";

    private int login_id;
    private String email;
    private String pass;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_LOGIN + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PASS + " TEXT"
                    + ")";

    public Login(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return login_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int login_id) {
        this.login_id = login_id;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
