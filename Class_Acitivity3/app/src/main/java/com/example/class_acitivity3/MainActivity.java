package com.example.class_acitivity3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInput, btnOutput, btnStudent;
    String name;
    String phone;
    int age;

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInput = findViewById(R.id.btnInput);
        btnOutput = findViewById(R.id.btnOutput);
        btnStudent = findViewById(R.id.btnStudent);

        //student 버튼 클릭시
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StudentActivity.class);
                startActivityForResult(intent,200);
            }
        });

        // input버튼 클릭시
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InputActivity.class);
                startActivityForResult(intent,100);
                // activity 열때... requestCode와 같이
            }
        });

        // output버튼 클릭시
        btnOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),OutputActivity.class);
//                intent.putExtra("name",name);
//                intent.putExtra("age",age);
//                intent.putExtra("phone",phone);
                Intent intent = new Intent(getApplicationContext(),OutputActivity.class);
                intent.putExtra("student",student);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK) {

            name = data.getStringExtra("name");
            age = data.getIntExtra("age",0);
            phone = data.getStringExtra("phone");

            Log.i("inputdata",name);
            Log.i("inputdata",age+"");
            Log.i("inputdata",phone);

            Toast.makeText(this,name+" , "+age+" , "+phone,Toast.LENGTH_SHORT).show();

        } else if (requestCode == 200 && resultCode == RESULT_OK) {

            student = (Student) data.getSerializableExtra("student");
            Log.i("student",student+"");
        }
    }
}