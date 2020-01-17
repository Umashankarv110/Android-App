package com.example.myprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {

    private TextView mTitle,mDetails,mCode,mOutput;
    private Menu del,update;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitle = findViewById(R.id.txtTitle);
        mDetails = findViewById(R.id.txtDetail);
        mCode = findViewById(R.id.txtCode);
        mOutput = findViewById(R.id.txtOutput);
        del = findViewById(R.id.b_delete);
        update = findViewById(R.id.b_update);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            mTitle.setText(bundle.getString("Title"));
            mDetails.setText(bundle.getString("Definition"));
            mCode.setText(bundle.getString("Code"));
            mOutput.setText(bundle.getString("Output"));

            key = bundle.getString("keyValue"); //Delete

        }

    }




    public void Delete_btn(View view) {
        final DatabaseReference  reference = FirebaseDatabase.getInstance().getReference("Quiz");
        reference.child(key).removeValue();
        Toast.makeText(this, "Item Deleted...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

/*
//menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m1 = getMenuInflater();
        m1.inflate(R.menu.action_update,menu);
        return super.onCreateOptionsMenu(menu);
    }//menu
    */

    public void Update_btn(View view) {
        startActivity(new Intent(getApplicationContext(),Update_Quiz_Activity.class)
        .putExtra("titleKey",mTitle.getText().toString())
                        .putExtra("detailKey",mDetails.getText().toString())
                        .putExtra("codeKey",mCode.getText().toString())
                        .putExtra("outputKey",mOutput.getText().toString())
                        .putExtra("key",key)
        );
        finish();
    }
}
