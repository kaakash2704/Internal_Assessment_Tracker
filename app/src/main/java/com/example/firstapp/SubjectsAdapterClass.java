package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SubjectsAdapterClass extends RecyclerView.Adapter<SubjectsAdapterClass.ViewHolder>{
    private ArrayList<SubjectList> subjects = new ArrayList<>();
    private Context context;
    String uid;
    public SubjectsAdapterClass(Context context,String uid){
            this.context = context;
            this.uid = uid;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt1.setText(subjects.get(position).getSubjectName());
        holder.txt2.setText(subjects.get(position).getSubjectCode());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = holder.getAdapterPosition();
                Toast.makeText(context, subjects.get(a).getSubjectName()+" Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(),Exam_main.class);
                intent.putExtra("type","add");
                intent.putExtra("subName",subjects.get(a).getSubjectName());
                intent.putExtra("subCode",subjects.get(a).getSubjectCode());
                intent.putExtra("IdAlloc",subjects.get(a).getIdAllocations());
                intent.putExtra("Uid",uid);
                context.startActivity(intent);
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = holder.getAdapterPosition();
                Toast.makeText(context, subjects.get(a).getSubjectName()+" Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(),Exam_main.class);
                intent.putExtra("type","view");
                intent.putExtra("subName",subjects.get(a).getSubjectName());
                intent.putExtra("subCode",subjects.get(a).getSubjectCode());
                intent.putExtra("IdAlloc",subjects.get(a).getIdAllocations());
                intent.putExtra("Uid",uid);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(ArrayList<SubjectList> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     private Button view,add;
        private TextView txt1;
        private TextView txt2;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent_subject);
            txt1 = itemView.findViewById(R.id.subName);
            txt2 = itemView.findViewById(R.id.subcode);
            view=itemView.findViewById(R.id.butview);
            add=itemView.findViewById(R.id.butenter);

        }
    }
}
