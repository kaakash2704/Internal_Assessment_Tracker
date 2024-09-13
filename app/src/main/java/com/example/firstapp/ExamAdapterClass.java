package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExamAdapterClass extends RecyclerView.Adapter<ExamAdapterClass.Exam_View_Holder>{
    ArrayList<ExamList> exam_list = new ArrayList<>();
    Context exam_Context;
    String action;
    String user_id,subCode,idAlloc;

    public ExamAdapterClass(Context exam_Context,String uid,String subCode,String idAlloc, String action) {
        this.exam_Context = exam_Context;
        this.user_id = uid;
        this.subCode = subCode;
        this.idAlloc = idAlloc;
        this.action=action;
    }
    @NonNull
    @Override
    public Exam_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_list,parent,false);
        Exam_View_Holder holder = new Exam_View_Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Exam_View_Holder holder, int position) {
        holder.exam.setText(exam_list.get(position).getExam());
        holder.exam_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = holder.getAdapterPosition();
                Toast.makeText(exam_Context, exam_list.get(a).getExam()+" is selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(exam_Context.getApplicationContext(),Student_main.class);
                intent.putExtra("exam",exam_list.get(a).getExam());
                intent.putExtra("userid",user_id);
                intent.putExtra("subCode",subCode);
                intent.putExtra("idAlloc",idAlloc);
                intent.putExtra("type",action);
                intent.putExtra("eMark",exam_list.get(a).eMark);
                Log.e("Emark",""+exam_list.get(a).eMark);
                intent.putExtra("tMark",exam_list.get(a).tMark);
                exam_Context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return exam_list.size();
    }
    public void setExam_list(ArrayList<ExamList> exam_list) {
        this.exam_list = exam_list;
        notifyDataSetChanged();
    }
    public class Exam_View_Holder extends RecyclerView.ViewHolder {
        private CardView exam_parent;
        private TextView exam;

        public Exam_View_Holder(@NonNull View itemView) {
            super(itemView);
            exam_parent = itemView.findViewById(R.id.exam_parent);
            exam = itemView.findViewById(R.id.exam);
        }
    }
}
