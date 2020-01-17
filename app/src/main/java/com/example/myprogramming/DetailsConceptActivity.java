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

public class DetailsConceptActivity extends AppCompatActivity {

    TextView conceptTitle,conceptAnswer,conceptExample,conceptDefinition;
    private Menu del,update;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_concept);

        conceptTitle = findViewById(R.id.txt_concept_Title);
        conceptDefinition = findViewById(R.id.txt_concept_Definition);
        conceptAnswer = findViewById(R.id.txt_concept_Answer);
        conceptExample = findViewById(R.id.txt_concept_Example);
        del = findViewById(R.id.b_delete);
        update = findViewById(R.id.b_update);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null){
            conceptTitle.setText(mBundle.getString("ConceptTitle"));
            conceptDefinition.setText(mBundle.getString("ConceptDefinition"));
            conceptAnswer.setText(mBundle.getString("ConceptAnswer"));
            conceptExample.setText(mBundle.getString("ConceptExample"));

            key = mBundle.getString("keyValue"); //Delete
        }
    }

    public void Delete_btn(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PyConcept");
        reference.child(key).removeValue();
        Toast.makeText(this, "Item Deleted...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainConceptActivity.class));
        finish();
    }
    public void Update_btn(View view) {
        startActivity(new Intent(getApplicationContext(), Update_Concept_Activity.class)
        .putExtra("ConceptTitleKey",conceptTitle.getText().toString())
        .putExtra("ConceptDefinitionKey",conceptAnswer.getText().toString())
        .putExtra("ConceptAnswerKey",conceptAnswer.getText().toString())
        .putExtra("ConceptExampleKey",conceptExample.getText().toString())
        .putExtra("key",key)
        );

    }

}
