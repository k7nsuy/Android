package com.example.class_activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    String tag = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Log.i(tag,tag+"onCreate()"); //i = information
        Toast.makeText(this,tag+"onCreate()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tag,tag+"onStart()"); //i = information
        Toast.makeText(this,tag+"onStart()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(tag,tag+"onResume()"); //i = information
        Toast.makeText(this,tag+"onResume()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(tag,tag+"onPause()"); //i = information
        Toast.makeText(this,tag+"onPause()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(tag,tag+"onStop()"); //i = information
        Toast.makeText(this,tag+"onStop()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(tag,tag+"onDestroy()"); //i = information
        Toast.makeText(this,tag+"onDestroy()",Toast.LENGTH_SHORT).show();
    }
}