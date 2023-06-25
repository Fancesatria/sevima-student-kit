package com.example.studentkit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.studentkit.API.ShowNotes;
import com.example.studentkit.databinding.ActivityNotesBinding;
import com.example.studentkit.databinding.AddNoteBinding;



public class NotesActivity extends AppCompatActivity {
    ActivityNotesBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.plusBtnKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotesActivity.this, AddNoteActivity.class));
            }
        });

        ShowNotes s = new ShowNotes(NotesActivity.this,bind);
        s.execute();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ShowNotes s = new ShowNotes(NotesActivity.this,bind);
        s.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowNotes s = new ShowNotes(NotesActivity.this,bind);
        s.execute();
    }
}