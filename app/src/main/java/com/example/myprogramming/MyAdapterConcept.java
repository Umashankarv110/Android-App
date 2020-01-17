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
public class MyAdapterConcept extends RecyclerView.Adapter<ConceptViewHolder>{

    private Context mContext;
    private List<QuizConceptData> myQuizList;

    public MyAdapterConcept(Context mContext, List<QuizConceptData> myQuizList) {
        this.mContext = mContext;
        this.myQuizList = myQuizList;
    }


    public void filteredList(ArrayList<QuizConceptData> filterList) {
        myQuizList = filterList;
        notifyDataSetChanged();
    }

    @Override
    public ConceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_concept_item, parent,false);
        return new ConceptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ConceptViewHolder holder, int position) {

        holder.mConceptTitle.setText(myQuizList.get(position).getConceptTitle());
        holder.mConceptDefinition.setText(myQuizList.get(position).getConcept_definitation());
        holder.mConceptAnswer.setText(myQuizList.get(position).getConceptAnswer());
        holder.mConceptExample.setText(myQuizList.get(position).getConceptExample());

        holder.mConceptCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsConceptActivity.class );
                intent.putExtra("ConceptTitle",myQuizList.get(holder.getAdapterPosition()).getConceptTitle());
                intent.putExtra("ConceptDefinition",myQuizList.get(holder.getAdapterPosition()).getConcept_definitation());
                intent.putExtra("ConceptAnswer",myQuizList.get(holder.getAdapterPosition()).getConceptAnswer());
                intent.putExtra("ConceptExample",myQuizList.get(holder.getAdapterPosition()).getConceptExample());
                intent.putExtra("keyValue",myQuizList.get(holder.getAdapterPosition()).getConceptKey());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myQuizList.size();
    }
}

//1st
class  ConceptViewHolder extends RecyclerView.ViewHolder{

    TextView mConceptTitle,mConceptAnswer,mConceptExample,mConceptDefinition;
    CardView mConceptCardView;

    public ConceptViewHolder(View itemView) {
        super(itemView);
        mConceptTitle = itemView.findViewById(R.id.myConceptTitle);
        mConceptDefinition =itemView.findViewById(R.id.myConceptDefinition);
        mConceptAnswer = itemView.findViewById(R.id.myConceptAnswer);
        mConceptExample = itemView.findViewById(R.id.myConceptExample);

        mConceptCardView = itemView.findViewById(R.id.myConceptCardView);
    }
}
