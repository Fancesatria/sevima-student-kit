package com.example.studentkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.studentkit.API.Login;
import com.example.studentkit.Component.SuccessDialog;
import com.example.studentkit.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }

    public boolean validasi(){
        EditText Username = bind.username;
        EditText Password = bind.password;
        if(Username.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
            Username.requestFocus();
            Username.setError("Harap diisi");
            Password.requestFocus();
            Password.setError("Harap diisi");
            return true;
        } else {
            SuccessDialog.message(LoginActivity.this, "Login berhasil", bind.getRoot());
        }
        return false;
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    public void login(View view) {
        if(validasi()){

        } else {
            Login l = new Login(bind, this);
            l.execute(bind.username.getText().toString(), bind.password.getText().toString());
        }
    }
}