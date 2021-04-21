package com.example.a21_04_21_gridview02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    TextView mvTitle;
    EditText mvEdit;
    ImageView mvPoster;
    Button mvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mvTitle = findViewById(R.id.mvText);
        mvEdit = findViewById(R.id.mvEdit);
        mvPoster = findViewById(R.id.mvPoster);
        mvButton = findViewById(R.id.mvButton);

        Intent intent = getIntent();
        int id = intent.getIntExtra("imgIds",0);
        String title = intent.getStringExtra("titles");

        mvTitle.setText(title);
        mvPoster.setImageResource(id);
        mvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int point = Integer.parseInt(mvEdit.getText().toString());
                Intent newIntent = new Intent(getApplicationContext(),MainActivity.class);
                newIntent.putExtra("point",point);
                newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                        Intent.FLAG_ACTIVITY_SINGLE_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newIntent);
                finish();
            }
        });
    }
}