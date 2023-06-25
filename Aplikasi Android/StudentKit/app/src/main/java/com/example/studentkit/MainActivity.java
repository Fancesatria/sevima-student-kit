package com.example.studentkit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studentkit.API.Login;
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

        //Toast.makeText(this, Login.iduser+"", Toast.LENGTH_SHORT).show();

        bind.signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Confirmation").setMessage(R.string.logout_ensure)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sp.clearData();

                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

            }});
    }


    public void chat(View view) {
        startActivity(new Intent(this, ChatActivity.class));
    }

    public void notes(View view) {
        startActivity(new Intent(this, NotesActivity.class));
    }

    public void quiz(View view) {
        startActivity(new Intent(this, QuizActivity.class));
    }

    public void imageGenerator(View view) {
        startActivity(new Intent(this, ImageGeneratorActivity.class));
    }
}