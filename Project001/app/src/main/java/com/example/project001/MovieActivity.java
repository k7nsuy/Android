package com.example.project001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    TextView mvTitle,mvDirector,mvActor;
    Button btnBack;
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mvActor = findViewById(R.id.mvActor);
        mvTitle = findViewById(R.id.mvTitle);
        mvDirector = findViewById(R.id.mvDirector);
        ivPoster = findViewById(R.id.ivPoster);

        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String actor = intent.getStringExtra("actor");
        String director = intent.getStringExtra("director");
        int img = intent.getIntExtra("imgIds",0);

        mvTitle.setText(title);
        mvActor.setText(actor);
        mvDirector.setText(director);
        ivPoster.setImageResource(img);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}