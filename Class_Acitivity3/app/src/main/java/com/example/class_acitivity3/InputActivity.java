package com.example.class_acitivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    EditText eName,eAge,ePhone;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        eName = findViewById(R.id.eName);
        eAge = findViewById(R.id.eAge);
        ePhone = findViewById(R.id.ePhone);

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name",eName.getText().toString());
                intent.putExtra("age",Integer.parseInt(eAge.getText().toString()));
                intent.putExtra("phone",ePhone.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}