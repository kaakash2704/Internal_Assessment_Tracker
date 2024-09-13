package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Subject_main extends AppCompatActivity {
    private RecyclerView relView;

    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_main);

        relView = findViewById(R.id.subject_view);

        String uid = getIntent().getExtras().getString("uid");


        TextView user = findViewById(R.id.user);
        user.setText("");
        ArrayList<SubjectList> subjects = new ArrayList<>();
        subjects.add(new SubjectList("English","ENG123","1"));
        subjects.add(new SubjectList("English2","ENG134","2"));
        subjects.add(new SubjectList("MobileProgramming","MP123","3"));
        SubjectsAdapterClass adapter = new SubjectsAdapterClass(Subject_main.this,uid);

        adapter.setSubjects(subjects);
        relView.setAdapter(adapter);
        relView.setLayoutManager(new LinearLayoutManager(Subject_main.this));




//        IP ip = new IP();
//        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip.getIP()+"/app/course.php", new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONArray a1=new JSONArray(response);
//                    ArrayList<SubjectList> subjects = new ArrayList<>();
//                    for(int i=0;i<a1.length();i++){
//                        JSONObject object = a1.getJSONObject(i);
//                        String idAlloc = object.getString("id");
//                        String courseCode = object.getString("course_code");
//                        String courseName = object.getString("course_name");
//                        subjects.add(new SubjectList(courseName,courseCode,idAlloc));
//                    }
//                    SubjectsAdapterClass adapter = new SubjectsAdapterClass(Subject_main.this,uid);
//                    adapter.setSubjects(subjects);
//
//                    relView.setAdapter(adapter);
//                    relView.setLayoutManager(new LinearLayoutManager(Subject_main.this));
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        },new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                android.util.Log.d("Bug",error.toString());
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("v1",uid);
//                return params;
//            }
//        };
//        RequestQueue queue = Volley.newRequestQueue(Subject_main.this);
//        queue.add(request);




    }

}