package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapterClass extends RecyclerView.Adapter<StudentAdapterClass.ViewHolder> {

    ArrayList<StudentList> students = new ArrayList<>();

    Context context;

    String uid,subCode,exam,idAlloc;

    int eMArks,tMarks;


    public StudentAdapterClass(Context context, String uid,String subCode,String exam,String idAlloc) {
        this.context = context;
        this.uid = uid;
        this.subCode = subCode;
        this.exam = exam;
        this.idAlloc = idAlloc;
    }

    public void setMarks(int eMArks,int tMarks){
        this.eMArks = eMArks;
        this.tMarks = tMarks;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt1.setText(students.get(position).getStudent_name());
        holder.txt2.setText(students.get(position).getUnique_id());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = holder.getAdapterPosition();
                Toast.makeText(context, students.get(a).student_name+" is Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(),Question_main.class);
                intent.putExtra("FacId",uid);
                intent.putExtra("SubjectCode",subCode);
                intent.putExtra("ExamId",exam);
                intent.putExtra("StudId",students.get(a).unique_id);
                intent.putExtra("IdAllocations",idAlloc);
                intent.putExtra("EMarks",eMArks);
                intent.putExtra("TMarks",tMarks);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
    public void setStudents(ArrayList<StudentList> students) {
        this.students = students;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView parent;
        TextView txt1;
        TextView txt2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.student_parent);
            txt1 = itemView.findViewById(R.id.student_name);
            txt2 = itemView.findViewById(R.id.unique_id);
        }
    }
}

