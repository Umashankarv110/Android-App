package com.example.myprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void Concept(View view) {
        startActivity(new Intent(getApplicationContext(), MainConceptActivity.class));
    }

    public void Program(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


    public void Interview(View view) {
        startActivity(new Intent(getApplicationContext(),MainInterviewQuizActivity.class));
    }

}
