package com.example.skuymppl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin;
            Button btnRegister;
            EditText txtUser;
            EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtUser = findViewById(R.id.email);
        txtPass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
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
                    Toast.makeText(this, "Selamat Datang\n" + username, Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                }
                break;

            case R.id.register:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class );
                startActivity(registerIntent);
                break;
        }
    }

}
