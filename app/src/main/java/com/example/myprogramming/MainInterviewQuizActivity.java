package com.example.myprogramming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainInterviewQuizActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<QuizInterviewData> myDataList;
    EditText txt_search;
    MyAdapterInterviewQuiz myAdapterInterviewQuiz;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interview_quiz);

        mRecyclerView = findViewById(R.id.recyclerQuizView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainInterviewQuizActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_search = findViewById(R.id.txt_Quiz_search);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Item.....");

        myDataList = new ArrayList<>();
        myAdapterInterviewQuiz = new MyAdapterInterviewQuiz(MainInterviewQuizActivity.this,myDataList);
        mRecyclerView.setAdapter(myAdapterInterviewQuiz);


        //Retrieve Details
        databaseReference = FirebaseDatabase.getInstance().getReference("InterviewQuiz");
        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataList.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    QuizInterviewData interviewQuizData = itemSnapshot.getValue(QuizInterviewData.class);
                    interviewQuizData.setQuizkey(itemSnapshot.getKey());
                    myDataList.add(interviewQuizData);
                }
                myAdapterInterviewQuiz.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        ArrayList<QuizInterviewData> filterList = new ArrayList<>();
        for (QuizInterviewData item: myDataList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        myAdapterInterviewQuiz.filteredList(filterList);
    }

    public void UploadInterViewQuiz(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_InterviewQuiz.class));
        finish();
    }
}
