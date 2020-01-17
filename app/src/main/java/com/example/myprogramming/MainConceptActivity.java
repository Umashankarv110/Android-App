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

public class MainConceptActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<QuizConceptData> myDataList;
    EditText txt_search;
    MyAdapterConcept myAdapterConcept;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_concept);

        mRecyclerView = findViewById(R.id.recyclerConceptView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainConceptActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_search = findViewById(R.id.txt_Concept_search);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Item.....");

        myDataList = new ArrayList<>();
        myAdapterConcept = new MyAdapterConcept(MainConceptActivity.this,myDataList);
        mRecyclerView.setAdapter(myAdapterConcept);


        //Retrieve Details
        databaseReference = FirebaseDatabase.getInstance().getReference("PyConcept");
        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myDataList.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    QuizConceptData conceptQuizData = itemSnapshot.getValue(QuizConceptData.class);
                    conceptQuizData.setConceptKey(itemSnapshot.getKey());
                    myDataList.add(conceptQuizData);
                }
                myAdapterConcept.notifyDataSetChanged();
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
        ArrayList<QuizConceptData> filterList = new ArrayList<>();
        for (QuizConceptData item: myDataList){
            if(item.getConceptTitle().toLowerCase().contains(text.toLowerCase())){
                 filterList.add(item);
            }
        }
        myAdapterConcept.filteredList(filterList);
    }

    public void UploadConcept(View view) {
        startActivity(new Intent(getApplicationContext(), Upload_Concept_Activity.class));
        finish();
    }
}
