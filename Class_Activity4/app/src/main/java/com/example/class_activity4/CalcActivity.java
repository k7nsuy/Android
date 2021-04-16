package com.example.class_activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalcActivity extends AppCompatActivity {

    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1",0);
        int num2 = intent.getIntExtra("num2",0);
        String op = intent.getStringExtra("op");
        switch (op) {
            case "+" :
                sum = num1 + num2;
            break;

            case "-" :
                sum = num1 - num2;
            break;

            case "*" :
                sum = num1 * num2;
            break;

            case "/" :
                sum = num1 / num2;
            break;
        }

        Button btn = findViewById(R.id.btnBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("sum",sum);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

    }
}