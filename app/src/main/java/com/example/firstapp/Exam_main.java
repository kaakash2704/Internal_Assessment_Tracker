package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class Exam_main extends AppCompatActivity {

    private RecyclerView exam_recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_main);
        exam_recycler = findViewById(R.id.exam_view);


        String subCode = getIntent().getExtras().getString("subCode");
        String action=getIntent().getExtras().getString("type");
        String uid = getIntent().getExtras().getString("Uid");
        String idAlloc = getIntent().getExtras().getString("IdAlloc");
        TextView subcodename = findViewById(R.id.subCodeName);
        subcodename.setText(subCode);

        IP ip = new IP();
        StringRequest request = new StringRequest(Request.Method.GET, "http://"+ip.getIP()+"/app/exam.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray a1=new JSONArray(response);
                    ArrayList<ExamList> arrayList = new ArrayList<>();
                    for(int i=0;i<a1.length();i++){
                        JSONObject object = a1.getJSONObject(i);
                        String exam_name = object.getString("Exam_name");
                        int eMark = object.getInt("each_sub_mark");
                        int tMark = object.getInt("total_mark");
//                        Log.e("exam",exam_name);
                        arrayList.add(new ExamList(exam_name,eMark,tMark));
                    }
                    ExamAdapterClass adaptar_class = new ExamAdapterClass(Exam_main.this,uid,subCode,idAlloc,action);
                    adaptar_class.setExam_list(arrayList);

                    exam_recycler.setAdapter(adaptar_class);
                    exam_recycler.setLayoutManager(new LinearLayoutManager(Exam_main.this));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                android.util.Log.d("Bug",error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("v1",Exam_main.this.getIntent().getExtras().getString("uid"));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Exam_main.this);
        queue.add(request);
    }
}