package com.example.class_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.nio.file.Files;

public class SmsActivity extends AppCompatActivity {

    EditText editNumber,editDate,editContent;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editNumber = findViewById(R.id.editNumber);
        editDate = findViewById(R.id.editDate);
        editContent = findViewById(R.id.editContent);
        btn = findViewById(R.id.btn);
        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            // 날짜를 String format 으로 바꿔줘야 함.
            String receivedDate = intent.getStringExtra("receivedDate");
            editNumber.setText(sender);
            editContent.setText(contents);
            editDate.setText(receivedDate);
        }
    }
}