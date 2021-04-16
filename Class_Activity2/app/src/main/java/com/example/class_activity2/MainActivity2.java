package com.example.class_activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView getIdPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getIdPw = findViewById(R.id.getIdPw);

        Intent intent = getIntent();
        String Id = intent.getStringExtra("Id");
        String Pw = intent.getStringExtra("Pw");
        getIdPw.setText(Id+" , "+Pw);

        Button btn = findViewById(R.id.btnClose);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}