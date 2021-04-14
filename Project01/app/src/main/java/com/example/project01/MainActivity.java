package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chk;
    TextView txt1,txt2;
    ImageView img;
    RadioGroup rGrp;
    Button rBtn;
    RadioButton[] rBs = new RadioButton[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1.findViewById(R.id.txt1);
        chk.findViewById(R.id.chk);

        txt2.findViewById(R.id.txt2);
        rGrp.findViewById(R.id.rGrp);

    }
}