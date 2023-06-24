package com.example.studentkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentkit.API.Register;
import com.example.studentkit.Component.ErrorDialog;
import com.example.studentkit.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding bind;
    EditText etEmail, etPassword, etConfirm, etUsername, etTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }

    public boolean validasi() {
        etEmail = bind.email;
        etPassword = bind.password;
        etConfirm = bind.confirmpassword;
        etUsername = bind.username;
        etTelp = bind.telp;
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        if(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etConfirm.getText().toString().isEmpty() || etUsername.getText().toString().isEmpty() || etTelp.getText().toString().isEmpty()) {
            etEmail.requestFocus();
            etEmail.setError("Harap diisi");

            etUsername.requestFocus();
            etUsername.setError("Harap diisi");

            etTelp.requestFocus();
            etTelp.setError("Harap diisi");

            etConfirm.requestFocus();
            etConfirm.setError("Harap diisi");
            return true;

        } else if (!etPassword.getText().toString().matches(etConfirm.getText().toString())){
            ErrorDialog.message(RegisterActivity.this, getString(R.string.unmatch), bind.getRoot());
            return true;
        } else if (!etEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            ErrorDialog.message(RegisterActivity.this, getString(R.string.is_email), bind.getRoot());
            return true;
        } else if(!etPassword.getText().toString().matches(regex) || etPassword.getText().toString().length() < 8 || !etConfirm.getText().toString().matches(regex) || etPassword.getText().toString().length() < 8){
            ErrorDialog.message(RegisterActivity.this, "Password harus terdisi dari minimal 8 digit, angka, dan karakter", bind.getRoot());
            return true;
        } else {
            Toast.makeText(this, "Harap tunggu...", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void buatAkun(View view) {
        if(validasi()){

        } else {
            Register r = new Register(bind, this);
            r.execute(bind.username.getText().toString(), bind.email.getText().toString(),
            bind.password.getText().toString(), bind.telp.getText().toString());
        }
    }
}