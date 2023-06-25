package com.example.studentkit.API;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.studentkit.Helper.ConnHelper;
import com.example.studentkit.Helper.SPHelper;
import com.example.studentkit.Model.ModelNote;
import com.example.studentkit.Note.NoteAdapter;
import com.example.studentkit.Response;
import com.example.studentkit.databinding.ActivityNotesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowNotes extends AsyncTask<String, Void, Response> {
    String url = "http://192.168.47.126:8000/api/pengguna/todo/index/3";
    Context context;
    ActivityNotesBinding bind;
    List<ModelNote> modelHomeList = new ArrayList<>();


    public ShowNotes(Context context, ActivityNotesBinding bind) {
        this.context = context;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {
        SPHelper sp = new SPHelper(context);
        ConnHelper ch = new ConnHelper(url, context);
        return ch.getData();
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("homes", response.data);
        bind.item.setHasFixedSize(true);
        bind.item.setLayoutManager(new LinearLayoutManager(context));
        if(response.data == null || response.data ==""){
        } else {
            try{
                JSONArray ja = new JSONArray(response.data);
                for(int i = 0; i<ja.length();i++){
                    JSONObject jsonObject = ja.getJSONObject(i);


                    modelHomeList.add(new ModelNote(jsonObject.getString("title"), jsonObject.getString("description"), jsonObject.getInt("idpengguna")));

                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        bind.item.setAdapter(new NoteAdapter(context, modelHomeList));
    }
}
