package com.example.myprogramming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//2nd
public class MyAdapterInterviewQuiz extends RecyclerView.Adapter<InterviewQuizViewHolder>{

    private Context mContext;
    private List<QuizInterviewData> myQuizList;

    public MyAdapterInterviewQuiz(Context mContext, List<QuizInterviewData> myQuizList) {
        this.mContext = mContext;
        this.myQuizList = myQuizList;
    }

    @Override
    public InterviewQuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_quiz_item, parent,false);
        return new InterviewQuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InterviewQuizViewHolder holder, int position) {

        holder.mQuizTitle.setText(myQuizList.get(position).getTitle());
        holder.mQuizAnswer.setText(myQuizList.get(position).getAnswer());
        holder.mQuizAnswer.setText(myQuizList.get(position).getExample());

        holder.mQuizCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsInterviewQuizActivity.class );
                intent.putExtra("QuizTitle",myQuizList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("QuizAnswer",myQuizList.get(holder.getAdapterPosition()).getAnswer());
                intent.putExtra("QuizExample",myQuizList.get(holder.getAdapterPosition()).getExample());
                intent.putExtra("keyValue",myQuizList.get(holder.getAdapterPosition()).getQuizkey());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myQuizList.size();
    }

    public void filteredList(ArrayList<QuizInterviewData> filterList) {
        myQuizList = filterList;
        notifyDataSetChanged();
    }
}

//1st
class InterviewQuizViewHolder extends RecyclerView.ViewHolder{

    TextView mQuizTitle,mQuizAnswer,mQuizExample;
    CardView mQuizCardView;

    public InterviewQuizViewHolder(View itemView) {
        super(itemView);
        mQuizTitle = itemView.findViewById(R.id.myQuizTitle);
        mQuizAnswer = itemView.findViewById(R.id.myQuizAnswer);
        mQuizExample = itemView.findViewById(R.id.myQuizExample);

        mQuizCardView = itemView.findViewById(R.id.myInterviewQuizView);
    }
}
