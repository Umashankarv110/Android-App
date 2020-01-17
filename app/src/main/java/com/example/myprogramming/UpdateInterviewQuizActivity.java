package com.example.myprogramming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateInterviewQuizActivity extends AppCompatActivity {

    EditText txt_title,txt_answer,txt_example;
    String key;
    DatabaseReference databaseReference;
    String title,answer,example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_interview_quiz);

        //
        txt_title = findViewById(R.id.update_quiz_Title);
        txt_answer = findViewById(R.id.update_quiz_Answer);
        txt_example=findViewById(R.id.update_quiz_Example);

//
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            txt_title.setText(bundle.getString("QuizTitleKey"));
            txt_answer.setText(bundle.getString("QuizAnswerKey"));
            txt_example.setText(bundle.getString("QuizExampleKey"));

            key = bundle.getString("key");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("InterviewQuiz").child(key);
    }

    public void btnUpdate_Quiz(View view) {
        title = txt_title.getText().toString().trim();
        answer = txt_answer.getText().toString().trim();
        example = txt_example.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        uploadInterviewQuiz();
        progressDialog.dismiss();

    }

    private void uploadInterviewQuiz() {
        QuizInterviewData quizInterviewData = new QuizInterviewData(
                title,answer,example
        );
        databaseReference.setValue(quizInterviewData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateInterviewQuizActivity.this, "Quiz Updated..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainInterviewQuizActivity.class));
                finish();
            }
        });
    }
}
