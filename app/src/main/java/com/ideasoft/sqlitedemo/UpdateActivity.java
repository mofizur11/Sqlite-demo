package com.ideasoft.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nameEt, phoneEt, ageEt;
    Button addBtn;
    DatabaseHelper databaseHelper;

    String name, phone, age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Person");

        nameEt = findViewById(R.id.nameEt);
        phoneEt = findViewById(R.id.phoneEt);
        ageEt = findViewById(R.id.ageEt);
        addBtn = findViewById(R.id.addBtn);

        databaseHelper = new DatabaseHelper(this);

        int uId = getIntent().getIntExtra("id", 1);
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        age = getIntent().getStringExtra("age");

        nameEt.setText(name);
        phoneEt.setText(phone);
        ageEt.setText(age);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                databaseHelper.update_database(uId, nameEt.getText().toString(), phoneEt.getText().toString(), ageEt.getText().toString());

                Toast.makeText(UpdateActivity.this, "Update Successfully!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                finish();
                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}