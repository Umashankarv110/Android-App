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

public class Upload_Quiz extends AppCompatActivity {


    EditText txt_title,txt_description,txt_code,txt_output;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__quiz);

        txt_title = findViewById(R.id.quiz_title);
        txt_description = findViewById(R.id.quiz_Detail);
        txt_code = findViewById(R.id.quiz_Code);
        txt_output = findViewById(R.id.quiz_output);
        upload = findViewById(R.id.upload);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txt_title.getText().toString().trim();
                String description = txt_description.getText().toString().trim();
                String code = txt_code.getText().toString().trim();
                String output = txt_output.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty() || code.isEmpty() || output.isEmpty()){
                    Toast.makeText(Upload_Quiz.this, "Data missing", Toast.LENGTH_SHORT).show();
                }else{
                    UploadQuiz();
                }

            }
        });
    }

    public void UploadQuiz(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        QuizData quizData = new QuizData(
                txt_title.getText().toString(),
                txt_description.getText().toString(),
                txt_code.getText().toString(),
                txt_output.getText().toString()
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Quiz")
                .child(myCurrentDateTime).setValue(quizData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Upload_Quiz.this, "Quiz Uploaded...", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Quiz.this, "Failed !!! \n Try Again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}
