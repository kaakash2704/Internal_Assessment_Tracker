package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText loginText,passwordText;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginText=(EditText) findViewById(R.id.login);
        passwordText=(EditText) findViewById(R.id.password);
        btnLogin =(Button) findViewById(R.id.buttonlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IP ip = new IP();
                StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip.getIP()+"/app/login.php", new Response.Listener<String>()  {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray a1=new JSONArray(response);
                            Log.e("user",loginText.getText().toString());
                            Log.e("pass",passwordText.getText().toString());
                       for(int i=0;i<a1.length();i++)
                       {
                            JSONObject obj=a1.getJSONObject(i);
                            String name=obj.getString("uid");
                            Log.e("name",name);
                        }
                            if(a1.length()==1)
                            {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Subject_main.class);

                                intent.putExtra("uid",loginText.getText().toString());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Login.this, "Invalid login", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        android.util.Log.d("Bug",error.toString());
                    }
                })

                {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("v1",loginText.getText().toString());
                        params.put("v2",passwordText.getText().toString());
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(request);

            }
        });
    }
}