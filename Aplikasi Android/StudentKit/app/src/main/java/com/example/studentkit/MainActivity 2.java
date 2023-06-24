package com.example.studentkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentkit.Helper.SPHelper;
import com.example.studentkit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        SPHelper sp = new SPHelper(this);
        bind.username.setText(sp.getUsername());
    }


    public void chat(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }
}