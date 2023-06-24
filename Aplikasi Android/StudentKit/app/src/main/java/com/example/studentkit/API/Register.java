package com.example.studentkit.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.studentkit.Helper.ConnHelper;
import com.example.studentkit.LoginActivity;
import com.example.studentkit.Response;
import com.example.studentkit.databinding.ActivityRegisterBinding;

import org.json.JSONObject;

public class Register extends AsyncTask<String, Void, Response> {
    ActivityRegisterBinding bind;
    Context context;

    String url = "http://192.168.47.126:8000/api/pengguna/register";

    public Register(ActivityRegisterBinding bind, Context context) {
        this.bind = bind;
        this.context = context;
    }

    @Override
    protected Response doInBackground(String... strings) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", strings[0]);
            jsonObject.put("password", strings[1]);
            jsonObject.put("email", strings[2]);
            jsonObject.put("telp", strings[2]);

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
                Toast.makeText(context, "Register succedeed, go to login", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, LoginActivity.class));
            } catch (Exception e){
                e.printStackTrace();
                Log.d("register", e.getMessage());
            }
        }
    }
}
