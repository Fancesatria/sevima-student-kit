package com.example.studentkit.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.studentkit.Helper.ConnHelper;
import com.example.studentkit.Helper.SPHelper;
import com.example.studentkit.MainActivity;
import com.example.studentkit.Response;
import com.example.studentkit.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AsyncTask<String, Void, Response> {
    String url = "http://192.168.47.126:8000/api/pengguna/login";
    ActivityLoginBinding bind;
    Context context;
    public static String token;

    public Login(ActivityLoginBinding bind, Context context) {
        this.bind = bind;
        this.context = context;
    }

    @Override
    protected Response doInBackground(String... strings) {
        JSONObject jo = new JSONObject();
        try{
            jo.put("email", strings[0]);
            jo.put("password", strings[1]);
        } catch(Exception e){
            e.printStackTrace();
        }

        ConnHelper ch = new ConnHelper(url, context);
        return ch.postData(jo);
    }


    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("data", response.data);
        if(response.data.isEmpty()){
            Toast.makeText(context, "Email or Password wrong", Toast.LENGTH_SHORT).show();
        } else{
            try {
                JSONObject jsonObject= new JSONObject(response.data);
                JSONObject j = (JSONObject) jsonObject.get("data");
                Log.d("token", j.getString("token"));
                SPHelper sp = new SPHelper(context);
                sp.setToken(j.getString("token"));
                sp.setEmail(j.getString("email"));
                sp.setUsername(j.getString("pengguna"));
                sp.setTelp(j.getString("telp"));

                context.startActivity(new Intent(context, MainActivity.class));
                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
