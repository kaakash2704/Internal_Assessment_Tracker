package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class Student_main extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        recyclerView = findViewById(R.id.student_view);
        String uid = getIntent().getExtras().getString("userid");
        String subCode = getIntent().getExtras().getString("subCode");
        String exam = getIntent().getExtras().getString("exam");
        String idAlloc = getIntent().getExtras().getString("idAlloc");
        Log.e("idalloc",""+idAlloc);
        int eMark = getIntent().getExtras().getInt("eMark");
        int tMark = getIntent().getExtras().getInt("tMark");
        Log.e("Each",""+eMark);
        Log.e("Total",""+tMark);

        String action=getIntent().getExtras().getString("type");
        Log.e("action",action);
        TextView subCodeNameExam = findViewById(R.id.subCodeNameExam);
        subCodeNameExam.setText(subCode+"_"+exam);

        IP ip = new IP();
        StringRequest request;//                        Log.e("student",student_name+student_id);
        if (action.equals("add"))
        {
            request = new StringRequest(Request.Method.POST, "http://"+ip.getIP()+"/app/students.php", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray a1 = new JSONArray(response);
                        ArrayList<StudentList> students = new ArrayList<>();
                        for (int i = 0; i < a1.length(); i++) {
                            JSONObject object = a1.getJSONObject(i);
                            String student_id = object.getString("ID");
                            String student_name = object.getString("stud_name");
                            Log.e("student",student_name+student_id);
                            students.add(new StudentList(student_name, student_id));
                        }
                        StudentAdapterClass adapterClass = new StudentAdapterClass(Student_main.this, uid, subCode, exam, idAlloc);
                        adapterClass.setMarks(eMark, tMark);
                        Log.e("eMark", "" + eMark);
                        adapterClass.setStudents(students);
                        recyclerView.setAdapter(adapterClass);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Student_main.this));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Bug", error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("v1", idAlloc);
                    return params;
                }
            };
        }
        else
        {
            request = new StringRequest(Request.Method.POST, "http://"+ip.getIP()+"/app/getMark.php", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray a1 = new JSONArray(response);
                        ArrayList<StudentList> students = new ArrayList<>();
                        for (int i = 0; i < a1.length(); i++) {
                            JSONObject object = a1.getJSONObject(i);
                            String student_id = object.getString("uid");
                            String student_name = object.getString("name");
                            float mark=0;
                            try {
                                mark = Float.parseFloat(object.getString("mark"));
                            }
                            catch (Exception e){}
                            Log.e("rate",""+mark);
                            Log.e("student",student_name+student_id);
                            students.add(new StudentList(student_name, student_id, mark));
                        }
                        StudentAdapterClass2 adapterClass = new StudentAdapterClass2(Student_main.this, uid, subCode, exam, idAlloc);
                        adapterClass.setMarks(eMark, tMark);
                        Log.e("eMark", "" + eMark);
                        adapterClass.setStudents(students);
                        recyclerView.setAdapter(adapterClass);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Student_main.this));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Bug", error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("v1", subCode);
                    params.put("v1", idAlloc);
                    return params;
                }
            };
        }
        RequestQueue queue = Volley.newRequestQueue(Student_main.this);
        queue.add(request);

    }
}