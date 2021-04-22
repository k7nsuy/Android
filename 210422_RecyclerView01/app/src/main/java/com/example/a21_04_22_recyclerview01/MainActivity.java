package com.example.a21_04_22_recyclerview01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Layout1 layout1;
    Button button,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.layout1);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        layout1.setIv(R.drawable.profile1);
        layout1.setTvName("홍길동");
        layout1.setTvPhone("010-0000-0001");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setIv(R.drawable.profile1);
                layout1.setTvName("홍길동");
                layout1.setTvPhone("010-0000-0001");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setIv(R.drawable.profile2);
                layout1.setTvName("홍길동2");
                layout1.setTvPhone("010-0000-0002");
            }
        });

    }
}