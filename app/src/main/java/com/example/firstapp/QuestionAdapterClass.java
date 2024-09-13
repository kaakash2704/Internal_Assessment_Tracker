package com.example.firstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapterClass extends RecyclerView.Adapter<QuestionAdapterClass.QuestionHolder>{
    Context question_context;
    String user_id,subCode,exam,studId;

    RatingReader reader;



    public QuestionAdapterClass(Context question_context, String user_id, String subCode, String exam, String studId,RatingReader reader) {
        this.question_context = question_context;
        this.user_id = user_id;
        this.subCode = subCode;
        this.exam = exam;
        this.studId = studId;
        this.reader = reader;
    }

    public int fullRating,stepRating;

    public void setRatings(int fullRating, int stepRating) {
        this.fullRating = fullRating;
        this.stepRating = stepRating;
    }



    ArrayList<QuestionList> questionLists = new ArrayList<>();




    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list,parent,false);
        QuestionHolder holder = new QuestionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        holder.ques.setText(questionLists.get(position).questions);
        holder.ratingBar.setNumStars(stepRating);
        Log.e("mark",""+fullRating);
        Log.e("tmark",""+stepRating);
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(question_context, ratingBar.getRating()+" Points", Toast.LENGTH_SHORT).show();
                reader.getRating(holder.getAdapterPosition(),ratingBar.getRating());
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionLists.size();
    }

    public void setQuestionLists(ArrayList<QuestionList> questionLists) {
        this.questionLists = questionLists;
        notifyDataSetChanged();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        CardView ques_card;
        TextView ques;
        RatingBar ratingBar;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            ques_card = itemView.findViewById(R.id.rating_parent);
            ques = itemView.findViewById(R.id.questions);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}

