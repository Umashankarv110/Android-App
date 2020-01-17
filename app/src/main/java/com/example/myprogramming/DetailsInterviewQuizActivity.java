package com.example.myprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsInterviewQuizActivity extends AppCompatActivity {

    TextView quizTitle,quizAnswer,quizExample;
    private Menu del,update;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_interview_quiz);

        quizTitle = findViewById(R.id.txt_quiz_Title);
        quizAnswer = findViewById(R.id.txt_quiz_Answer);
        quizExample = findViewById(R.id.txt_quiz_Example);
        del = findViewById(R.id.b_delete);
        update = findViewById(R.id.b_update);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null){
            quizTitle.setText(mBundle.getString("QuizTitle"));
            quizAnswer.setText(mBundle.getString("QuizAnswer"));
            quizExample.setText(mBundle.getString("QuizExample"));

            key = mBundle.getString("keyValue"); //Delete
        }
    }

    public void Delete_btn(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("InterviewQuiz");
        reference.child(key).removeValue();
        Toast.makeText(this, "Item Deleted...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainInterviewQuizActivity.class));
        finish();
    }

    public void Update_btn(View view) {
        startActivity(new Intent(getApplicationContext(),UpdateInterviewQuizActivity.class)
                .putExtra("QuizTitleKey",quizTitle.getText().toString())
                .putExtra("QuizAnswerKey",quizAnswer.getText().toString())
                .putExtra("QuizExampleKey",quizExample.getText().toString())
                .putExtra("key",key)
        );

    }

}
