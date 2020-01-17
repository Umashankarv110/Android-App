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

public class Update_Concept_Activity extends AppCompatActivity {

    EditText txt_title,txt_definition, txt_answer, txt_example;
    String key;
    DatabaseReference databaseReference;
    String title,defination, answer, example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_concept);

        //
        txt_title = findViewById(R.id.update_concept_Title);
        txt_definition = findViewById(R.id.update_concept_Definition);
        txt_answer = findViewById(R.id.update_concept_Answer);
        txt_example=findViewById(R.id.update_concept_Example);

//
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            txt_title.setText(bundle.getString("ConceptTitleKey"));
            txt_definition.setText(bundle.getString("ConceptDefinitionKey"));
            txt_answer.setText(bundle.getString("ConceptAnswerKey"));
            txt_example.setText(bundle.getString("ConceptExampleKey"));

            key = bundle.getString("key");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("PyConcept").child(key);
    }

    public void btnUpdate_Quiz(View view) {
        title = txt_title.getText().toString().trim();
        defination = txt_definition.getText().toString().trim();
        answer = txt_answer.getText().toString().trim();
        example = txt_example.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        uploadInterviewQuiz();
        progressDialog.dismiss();

    }

    private void uploadInterviewQuiz() {
        QuizConceptData quizInterviewData = new QuizConceptData(
                title,answer,example,defination
        );
        databaseReference.setValue(quizInterviewData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Update_Concept_Activity.this, "Quiz Updated..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainConceptActivity.class));
                finish();
            }
        });
    }
}