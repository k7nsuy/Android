package com.example.linearlayoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        Button button1 = new Button(this);
        button1.setText("버튼1");
        button1.setLayoutParams(params);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "You clicked the button1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button button2 = new Button(this);
        button2.setText("버튼2");
        button2.setLayoutParams(params);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                "You clicked the button2",
                Toast.LENGTH_SHORT).show();
            }
        });
        layout.addView(button1);
        layout.addView(button2);
        setContentView(layout);
//        setContentView(R.layout.activity_main);
    }
}