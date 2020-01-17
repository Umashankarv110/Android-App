package com.example.myprogramming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<QuizViewHolder> {

    private Context mContext;
    private List<QuizData> myQuizList;
    private int lastPosition = -1;

    public MyAdapter(Context mContext, List<QuizData> myQuizList) {
        this.mContext = mContext;
        this.myQuizList = myQuizList;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item,viewGroup,false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuizViewHolder holder, int i) {

        holder.mTitle.setText(myQuizList.get(i).getTitle());
        holder.mDetail.setText(myQuizList.get(i).getDefination());
        holder.mCode.setText(myQuizList.get(i).getCode());
        holder.mSolution.setText(myQuizList.get(i).getOutput());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("Title", myQuizList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Definition", myQuizList.get(holder.getAdapterPosition()).getDefination());
                intent.putExtra("Code", myQuizList.get(holder.getAdapterPosition()).getCode());
                intent.putExtra("Output", myQuizList.get(holder.getAdapterPosition()).getOutput());

                intent.putExtra("keyValue",myQuizList.get(holder.getAdapterPosition()).getKey());   //For Delete.
                mContext.startActivity(intent);
            }

        });
        setAnimation(holder.itemView,i);
    }
/// For Animation
    public void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);

            animation.setDuration(800);
            viewToAnimate.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return myQuizList.size();
    }

    public void filteredList(ArrayList<QuizData> filterList) {
        myQuizList = filterList;
        notifyDataSetChanged();
    }
}

class QuizViewHolder extends RecyclerView.ViewHolder{

    TextView mTitle, mDetail,mCode, mSolution;
    CardView mCardView;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.myTitle);
        mDetail =itemView.findViewById(R.id.myDefination);
        mCode = itemView.findViewById(R.id.myCode);
        mSolution = itemView.findViewById(R.id.myOutput);
        mCardView  = itemView.findViewById(R.id.myCardView);
    }



}
