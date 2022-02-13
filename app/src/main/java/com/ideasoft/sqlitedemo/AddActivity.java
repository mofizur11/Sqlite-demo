package com.ideasoft.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText nameEt, phoneEt, ageEt;
    Button addBtn;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Person");

        nameEt = findViewById(R.id.nameEt);
        phoneEt = findViewById(R.id.phoneEt);
        ageEt = findViewById(R.id.ageEt);
        addBtn = findViewById(R.id.addBtn);

        databaseHelper = new DatabaseHelper(this);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEt.getText().toString();
                String phone = phoneEt.getText().toString();
                String age = ageEt.getText().toString();

                if (name.isEmpty() && phone.isEmpty() && age.isEmpty()) {
                    nameEt.setError("Enter Name");
                    phoneEt.setError("Enter Phone");
                    ageEt.setError("Enter Age");
                }

                databaseHelper.addNewPerson(name, phone, age);

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
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