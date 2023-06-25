package com.example.studentkit.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.studentkit.Helper.ConnHelper;
import com.example.studentkit.Helper.SPHelper;
import com.example.studentkit.MainActivity;
import com.example.studentkit.NotesActivity;
import com.example.studentkit.Response;
import com.example.studentkit.databinding.ActivityLoginBinding;
import com.example.studentkit.databinding.ActivityNotesBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class DeleteNote extends AsyncTask<String, Void, Response> {
    String url = "http://192.168.47.126:8000/api/pengguna/todo/delete/";
    public static int iduser;
    ActivityNotesBinding bind;
    Context context;
    public static String token;

    public DeleteNote(ActivityNotesBinding bind, Context context) {
        this.bind = bind;
        this.context = context;
    }

    @Override
    protected Response doInBackground(String... strings) {
        url += Integer.parseInt(strings[0]);

        ConnHelper ch = new ConnHelper(url, context);
        return ch.delData();
    }


    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("data", response.data);
        if(response.data.isEmpty()){
            Toast.makeText(context, "Data cannot be deleted", Toast.LENGTH_SHORT).show();
        } else{

            Toast.makeText(context, "Data deleted succesfully", Toast.LENGTH_SHORT).show();
            //context.startActivity(new Intent(context, NotesActivity.class));

        }
    }
}
