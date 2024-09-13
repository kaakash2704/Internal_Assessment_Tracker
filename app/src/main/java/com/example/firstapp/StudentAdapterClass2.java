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

public class StudentAdapterClass2 extends RecyclerView.Adapter<StudentAdapterClass2.ViewHolder> {

    ArrayList<StudentList> students = new ArrayList<>();

    Context context;

    String uid,subCode,exam,idAlloc;

    int eMArks,tMarks;


    public StudentAdapterClass2(Context context, String uid,String subCode,String exam,String idAlloc) {
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
    public StudentAdapterClass2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list1,parent,false);
        StudentAdapterClass2.ViewHolder holder = new StudentAdapterClass2.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapterClass2.ViewHolder holder, int position) {
        holder.txt1.setText(students.get(position).getStudent_name());
        holder.txt2.setText(students.get(position).getUnique_id());
        holder.mark.setText(students.get(position).getMark()+"");
        Log.e("cmark",students.get(position).getMark()+"");
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
        TextView mark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.student_parent);
            txt1 = itemView.findViewById(R.id.student_name);
            txt2 = itemView.findViewById(R.id.unique_id);
            mark=itemView.findViewById(R.id.txtmark);

        }
    }
}

