package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button[] btns = new Button[16];
    int[] btn = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,
            R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,
            R.id.btn9,R.id.btnAdd,R.id.btnCancel,R.id.btnDiv,
            R.id.btnMul,R.id.btnResult,R.id.btnSub};
    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("계산기");

        tView = findViewById(R.id.tView01);
        for(int i = 0; i < btns.length; i++) {
            btns[i] = findViewById(btn[i]);
            }
    }

}
