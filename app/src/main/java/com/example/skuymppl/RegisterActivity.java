package com.example.skuymppl;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.skuymppl.Database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;

    private DatabaseHelper db;
    private Button submit;

    private EditText inpNama;
    private EditText inpEmail;
    private EditText inpPass;
    private EditText inpPassKon;
    private EditText inpAlamat;
    private EditText inpNotelp;
    private EditText inpNoktp;

    protected String email;
    private String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        inpNama = findViewById(R.id.inpNama);
        inpNama.setOnClickListener(this);
        inpEmail = findViewById(R.id.inpEmail);
        inpEmail.setOnClickListener(this);
        inpPass = findViewById(R.id.inpPass);
        inpPass.setOnClickListener(this);
        inpPassKon = findViewById(R.id.inpPasskon);
        inpPassKon.setOnClickListener(this);
        inpAlamat = findViewById(R.id.inpAlamat);
        inpAlamat.setOnClickListener(this);
        inpNoktp = findViewById(R.id.inpNoKtp);
        inpNoktp.setOnClickListener(this);
        inpNotelp = findViewById(R.id.inpNoTelp);
        inpNotelp.setOnClickListener(this);
        submit = findViewById(R.id.submitRegister);
        submit.setOnClickListener(this);

        db = new DatabaseHelper(this);
        Button browse = findViewById(R.id.browse);
        browse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitRegister:
                String nama = inpNama.getText().toString();
                email = inpEmail.getText().toString();
                String pass = inpPass.getText().toString();
                String passKonfirm = inpPassKon.getText().toString();
                String alamat = inpAlamat.getText().toString();
                String notelp = inpNotelp.getText().toString();
                String noktp = inpNoktp.getText().toString();
                String ktp = picturePath;

                if(nama.isEmpty()){
                    inpNama.setError("Silahkan masukkan nama");
                }
                if(email.isEmpty()){
                    inpEmail.setError("Silahkan masukkan email");
                }

                if(pass.isEmpty()){
                    inpPass.setError("Silahkan masukkan password");
                } else {
                    if(pass.length() <= 12 && pass.length() >= 8){
                        inpPass.setError("Password adalah karakter berjumlah 8-12");
                    }
                }
                if(pass != passKonfirm){
                    inpPassKon.setError("Password tidak sama");
                }
                if(alamat.isEmpty()){
                    inpAlamat.setError("Silahkan masukkan alamat lengkap anda");
                }
                if(notelp.isEmpty()){
                    inpNotelp.setError("Silahkan masukkan nomor telepon");
                }
                if(noktp.isEmpty()){
                    inpNoktp.setError("Silahkan masukkan nomor ktp/sim");
                } else {
                    if(noktp.length() == 13 || noktp.length() == 16){
                        inpNoktp.setError("nomor tidak valid");
                    }
                }
                if(ktp.isEmpty()){
                    Toast.makeText(this, "masukkan foto ktp anda" + ktp, Toast.LENGTH_SHORT).show();
                }

                db.insertUser(nama, email, pass, alamat, notelp, noktp, ktp);
                Intent regisIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(regisIntent);
                finish();

            case R.id.browse:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = findViewById(R.id.inpFoto);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }

}
