package com.example.sql_empl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextDept, editTextSalary;
    private Button buttonInsert;

    // Database Helper Object
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextDept = findViewById(R.id.editTextDept);
        editTextSalary = findViewById(R.id.editTextSalary);
        buttonInsert = findViewById(R.id.buttonInsert);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Insert record on button click
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ename = editTextName.getText().toString().trim();
                String dept = editTextDept.getText().toString().trim();
                String salaryStr = editTextSalary.getText().toString().trim();

                if (ename.isEmpty() || dept.isEmpty() || salaryStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double salary = Double.parseDouble(salaryStr);

                // Insert the record into the database
                boolean isInserted = dbHelper.insertEmployee(ename, dept, salary);

                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error inserting record", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
