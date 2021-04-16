package com.example.class_acitivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity {

    EditText sNum,sName,sMajor;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        sNum = findViewById(R.id.sNum);
        sName = findViewById(R.id.sName);
        sMajor = findViewById(R.id.sMajor);

        btn = findViewById(R.id.btnStud);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student();
                student.setsNo(Integer.parseInt(sNum.getText().toString()));
                student.setsName(sName.getText().toString());
                student.setsMajor(sMajor.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("student",student);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}