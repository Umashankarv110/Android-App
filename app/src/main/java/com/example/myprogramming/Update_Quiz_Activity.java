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
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update_Quiz_Activity extends AppCompatActivity {

    EditText txt_title,txt_description,txt_code,txt_output;
    Button upload;
    String key;
    String title,detail,code,output;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__quiz_);

//get Id from xml
        txt_title = findViewById(R.id.quiz_title);
        txt_description = findViewById(R.id.quiz_Detail);
        txt_code = findViewById(R.id.quiz_Code);
        txt_output = findViewById(R.id.quiz_output);
        upload = findViewById(R.id.upload);

//get data from Detail activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            txt_title.setText(bundle.getString("titleKey"));
            txt_description.setText(bundle.getString("detailKey"));
            txt_code.setText(bundle.getString("codeKey"));
            txt_output.setText(bundle.getString("outputKey"));
            key = bundle.getString("key");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Quiz").child(key);
    }

    public void UpdateData(View view) {

        title = txt_title.getText().toString().trim();
        detail = txt_description.getText().toString().trim();
        code = txt_code.getText().toString().trim();
        output = txt_output.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();
        uploadQuiz();
        progressDialog.dismiss();
    }

    private void uploadQuiz() {

        QuizData quizData = new QuizData(
                title,
                detail,
                code,
                output
        );

        databaseReference.setValue(quizData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Update_Quiz_Activity.this, "Quiz Updated..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }


}
