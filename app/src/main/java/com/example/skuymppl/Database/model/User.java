package com.example.skuymppl.Database.model;

public class User {
    public static final String TABLE_NAME = "user";

    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_FOTO = "foto";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_NOTELP = "notelp";
    public static final String COLUMN_NOKTP = "noktp";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_KTP = "ktp";
    public static final String COLUMN_SIM = "sim";
    public static final String COLUMN_POINT = "point";

    private int user_id;
    private String nama;
    private String foto;
    private String email;
    private String password;
    private String notelp;
    private String noktp;
    private String alamat;
    private String ktp;
    private String sim;
    private String point;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAMA + " TEXT,"
                    + COLUMN_FOTO + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PASS + " TEXT,"
                    + COLUMN_NOTELP + " TEXT,"
                    + COLUMN_NOKTP + " TEXT,"
                    + COLUMN_ALAMAT + " TEXT,"
                    + COLUMN_KTP + " TEXT,"
                    + COLUMN_SIM + " TEXT,"
                    + COLUMN_POINT + " INTEGER DEFAULT '0'"
                    + ")";

    public User() {
    }

    public User(int id, String nama, String foto, String email, String password, String notelp, String noktp, String alamat, String ktp, String sim, String point) {
        this.user_id = user_id;
        this.nama = nama;
        this.foto = foto;
        this.email = email;
        this.password = password;
        this.notelp = notelp;
        this.noktp = noktp;
        this.alamat = alamat;
        this.ktp = ktp;
        this.sim = sim;
        this.point = point;
    }

    public int getId() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String foto) {
        this.password = password;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String noktp) {
        this.notelp = notelp;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
