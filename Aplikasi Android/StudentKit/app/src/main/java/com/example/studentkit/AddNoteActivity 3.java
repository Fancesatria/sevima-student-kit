package com.example.studentkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.studentkit.API.AddNotes;
import com.example.studentkit.Component.ErrorDialog;
import com.example.studentkit.Helper.SPHelper;
import com.example.studentkit.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {
    ActivityAddNoteBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bind.title.getText().toString().isEmpty() || bind.description.getText().toString().isEmpty()){
                    ErrorDialog.message(AddNoteActivity.this, "All field must be filled", bind.getRoot());
                } else {
                    AddNotes n = new AddNotes(bind, AddNoteActivity.this);
                    n.execute(bind.title.getText().toString(), bind.description.getText().toString());
                }
            }
        });
    }
}