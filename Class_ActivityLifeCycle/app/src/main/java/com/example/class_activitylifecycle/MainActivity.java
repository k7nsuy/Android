package com.example.class_activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String tag = "mainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
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
    protected void onRestart() {
        super.onRestart();
        Log.i(tag,tag+"onRestart()"); //i = information
        Toast.makeText(this,tag+"onRestart()",Toast.LENGTH_SHORT).show();
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