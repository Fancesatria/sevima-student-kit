package com.example.studentkit.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.studentkit.Helper.ConnHelper;
import com.example.studentkit.Helper.SPHelper;
import com.example.studentkit.LoginActivity;
import com.example.studentkit.NotesActivity;
import com.example.studentkit.Response;
import com.example.studentkit.databinding.ActivityAddNoteBinding;
import com.example.studentkit.databinding.ActivityRegisterBinding;
import com.example.studentkit.databinding.AddNoteBinding;

import org.json.JSONObject;

public class AddNotes extends AsyncTask<String, Void, Response> {
    ActivityAddNoteBinding bind;
    Context context;

    String url = "http://192.168.47.126:8000/api/pengguna/todo/add";

    public AddNotes(ActivityAddNoteBinding bind, Context context) {
        this.bind = bind;
        this.context = context;
    }

    @Override
    protected Response doInBackground(String... strings) {
        JSONObject jsonObject = new JSONObject();
        try{
            SPHelper sp = new SPHelper(context);
            jsonObject.put("title", strings[0]);
            jsonObject.put("description", strings[1]);
            jsonObject.put("idpengguna", 3);

        } catch (Exception e){
            e.printStackTrace();
        }

        ConnHelper con = new ConnHelper(url, context);
        return con.postData(jsonObject);
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("data", response.data);
        if(response.data.isEmpty()){
            Toast.makeText(context, "Input failure", Toast.LENGTH_SHORT).show();
        } else {
            try{
                Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, NotesActivity.class));
            } catch (Exception e){
                e.printStackTrace();
                Log.d("note", e.getMessage());
            }
        }
    }
}
