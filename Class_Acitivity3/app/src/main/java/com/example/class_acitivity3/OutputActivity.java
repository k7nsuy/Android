package com.example.class_acitivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        tv = findViewById(R.id.tvDisplay);
        btn = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        tv.setText(student.getsNo() + " , " + student.getsName() + " , " + student.getsMajor());
//        String name = intent.getStringExtra("name");
//        int age = intent.getIntExtra("age",0);
//        String phone = intent.getStringExtra("phone");
//        tv.setText(name+ ","+age+","+phone);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}