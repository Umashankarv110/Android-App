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

public class Upload_Concept_Activity extends AppCompatActivity {

    EditText txt_title,txt_definition,txt_answer,txtexaple;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_concept);

        txt_title = findViewById(R.id.upload_concept_Title);
        txt_definition = findViewById(R.id.upload_concept_Definition);
        txt_answer = findViewById(R.id.upload_quiz_Answer);
        txtexaple = findViewById(R.id.upload_quiz_Example);
    }

    public void Upload_Quiz(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        QuizConceptData quizData = new QuizConceptData(
                txt_title.getText().toString(),
                txt_definition.getText().toString(),
                txt_answer.getText().toString(),
                txtexaple.getText().toString()
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("PyConcept")
                .child(myCurrentDateTime).setValue(quizData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Upload_Concept_Activity.this, "Quiz Uploaded...", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                finish();
                Intent intent = new Intent(getApplicationContext(), MainConceptActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Concept_Activity.this, "Failed !!! \n Try Again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
