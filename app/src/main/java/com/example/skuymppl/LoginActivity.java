package com.example.skuymppl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.skuymppl.Database.DatabaseHelper;
import com.example.skuymppl.Database.model.Login;
import com.example.skuymppl.Database.model.User;
import com.google.android.material.snackbar.Snackbar;
import android.content.SharedPreferences;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin;
            Button btnRegister;
            EditText txtUser;
            EditText txtPass;
            DatabaseHelper db;

    public static final String MyPREFERENCES = "LoggedUser" ;
    public static final String email = "admin" ;
    public static final String pass = "admin" ;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        db = new DatabaseHelper(this);
        txtUser = findViewById(R.id.email);
        txtPass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                String username = txtUser.getText().toString();
                String password = txtPass.getText().toString();
                if(username.isEmpty()){
                    txtUser.setError("Silahkan masukkan username / email");
                }
                else if(password.isEmpty()){
                    txtPass.setError("Silahkan masukkan password");
                }
                else {
                    User currentUser = db.Authenticate(new User(username, password));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Toast.makeText(this, "Login Sukses!", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(email, username);
                        editor.putString(pass, password);
                        editor.commit();

                        //User Logged in Successfully Launch You home screen activity
                        Log.d("LOOOGIIIN", "onClick: ");
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {

                        //User Logged in Failed
                        Toast.makeText(this, "Login Gagal!", Toast.LENGTH_SHORT).show();

                    }
                }
                break;

            case R.id.register:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class );
                startActivity(registerIntent);
                break;
        }
    }

}
