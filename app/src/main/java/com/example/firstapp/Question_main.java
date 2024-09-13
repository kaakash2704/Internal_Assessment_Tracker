package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Question_main extends AppCompatActivity implements RatingReader{

    RecyclerView rating_view;
    Button submit;

    int qid;

    ArrayList<Float> ratings = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_main);
        rating_view = findViewById(R.id.rating_view);
        submit = findViewById(R.id.submit);

        int eMarks = getIntent().getExtras().getInt("EMarks");
        int tMarks = getIntent().getExtras().getInt("TMarks");
        String user_id = getIntent().getExtras().getString("FacId");
        Log.e("Facid",user_id);
        String subCode = getIntent().getExtras().getString("SubjectCode");
        String exam = getIntent().getExtras().getString("ExamId");
        String StudId = getIntent().getExtras().getString("StudId");
        String idAlloc = getIntent().getExtras().getString("IdAllocations");
        TextView ans_src = findViewById(R.id.ans_script);
        ans_src.setText(subCode+"_"+exam+"_"+StudId);



        IP ip = new IP();
        StringRequest request = new StringRequest(Request.Method.POST, "http://"+ip.getIP()+"/app/questions.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONArray a1=new JSONArray(response);
                    ArrayList<QuestionList> questionLists = new ArrayList<>();
                    for(int i=0;i<a1.length();i++){
                        JSONObject object = a1.getJSONObject(i);
                        int qid = object.getInt("qid");
                        String question = object.getString("question");
                        String exam = object.getString("exam");
                        questionLists.add(new QuestionList(question,qid,exam));
                        ratings.add(0f);
                    }
                    QuestionAdapterClass adapter = new QuestionAdapterClass(Question_main.this,user_id,subCode,exam,StudId,Question_main.this);
                    adapter.setRatings(tMarks,eMarks);
                    adapter.setQuestionLists(questionLists);

                    rating_view.setAdapter(adapter);
                    rating_view.setLayoutManager(new LinearLayoutManager(Question_main.this));

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
                params.put("v1",idAlloc);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(Question_main.this);
        queue.add(request);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request1 = new StringRequest(Request.Method.POST, "http://" + ip.getIP() + "/mark.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Question_main.this, "Marks entered!", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Question_main.this, End.class);
//                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        float total = 0;
                        for(float f: ratings){
                            total = total + f;
                        }
                        params.put("uid",StudId);
                        params.put("ratings",""+total);
                        params.put("exam",exam);
                        params.put("subCode",subCode);
                        return params;
                    }
                };
                RequestQueue queue1 = Volley.newRequestQueue(Question_main.this);
                queue1.add(request1);
            }
        });


    }

    @Override
    public void getRating(int position, float value) {
        ratings.set(position,value);
    }
}