package com.example.studentkit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.studentkit.API.DeleteNote;
import com.example.studentkit.API.Login;
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
        s.execute(String.valueOf(Login.iduser));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        ShowNotes s = new ShowNotes(NotesActivity.this,bind);
        s.execute(String.valueOf(Login.iduser));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowNotes s = new ShowNotes(NotesActivity.this,bind);
        s.execute(String.valueOf(Login.iduser));
    }

    public void deleteNote(String id){
        AlertDialog.Builder alert = new AlertDialog.Builder(NotesActivity.this);
        alert.setTitle("Confirmation").setMessage(R.string.del_ensure)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DeleteNote n = new DeleteNote(bind, NotesActivity.this);
                        n.execute(id);
                        ShowNotes s = new ShowNotes(NotesActivity.this,bind);
                        s.execute(String.valueOf(Login.iduser));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}