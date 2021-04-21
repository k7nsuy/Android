package com.example.a2021_04_21_listview02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.file.Files;

public class PosterActivity extends AppCompatActivity {

    int[] imgIds = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,
            R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,
            R.drawable.mov08,R.drawable.mov09,R.drawable.mov10};

    TextView tvTitle;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        tvTitle = findViewById(R.id.tvTitle);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int position = intent.getIntExtra("position",0);
        tvTitle.setText(title);
        imageView.setImageResource(imgIds[position]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}