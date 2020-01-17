package com.example.myprogramming;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Upload_InterviewQuiz extends AppCompatActivity {

    EditText txt_title,txt_answer,txtexaple;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__interview_quiz);

        txt_title = findViewById(R.id.upload_quiz_Title);
        txt_answer = findViewById(R.id.upload_quiz_Answer);
        txtexaple=findViewById(R.id.upload_quiz_Example);
    }

    public void Upload_Quiz(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        QuizInterviewData quizData = new QuizInterviewData(
                txt_title.getText().toString(),
                txt_answer.getText().toString(),
                txtexaple.getText().toString()
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("InterviewQuiz")
                .child(myCurrentDateTime).setValue(quizData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Upload_InterviewQuiz.this, "Quiz Uploaded...", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                finish();
                Intent intent = new Intent(getApplicationContext(),MainInterviewQuizActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_InterviewQuiz.this, "Failed !!! \n Try Again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
