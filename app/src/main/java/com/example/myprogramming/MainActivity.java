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
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<QuizData> myQuizList;
    EditText textView;
    MyAdapter myAdapter;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        //Search
        textView = findViewById(R.id.txt_search);


        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        myQuizList = new ArrayList<>();
        myAdapter = new MyAdapter(MainActivity.this, myQuizList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Quiz");
        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myQuizList.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    QuizData quizData = itemSnapshot.getValue(QuizData.class);

                    quizData.setKey(itemSnapshot.getKey());     //delete

                    myQuizList.add(quizData);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        //Search
        textView.addTextChangedListener(new TextWatcher() {
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

    private void filter(String txt) {
        ArrayList<QuizData> filterList = new ArrayList<>();
        for(QuizData item: myQuizList){
            if(item.getTitle().toLowerCase().contains(txt.toLowerCase())){
                filterList.add(item);
            }
        }
        myAdapter.filteredList(filterList);
    }

    public void UploadQuiz(View view) {
        startActivity(new Intent(this,Upload_Quiz.class));
    }
}