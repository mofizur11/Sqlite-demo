package com.ideasoft.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nameEt, phoneEt;
    Button addBtn;


    private ArrayList<Model> list;
    private Adapter adapter;
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.nameEt);
        phoneEt = findViewById(R.id.phoneEt);
        addBtn = findViewById(R.id.addBtn);



        list = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        list = databaseHelper.readPersonList();
        adapter = new Adapter(MainActivity.this,list);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEt.getText().toString();
                String phone = phoneEt.getText().toString();

                if (name.isEmpty() && phone.isEmpty()) {
                  nameEt.setError("Enter Name");
                  phoneEt.setError("Enter Phone");
                }

                databaseHelper.addNewPerson(name,phone);

                Toast.makeText(MainActivity.this, "Person added", Toast.LENGTH_SHORT).show();
                nameEt.setText("");
                phoneEt.setText("");

            }
        });
    }
}