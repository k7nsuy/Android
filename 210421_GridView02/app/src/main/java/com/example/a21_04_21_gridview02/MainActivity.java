package com.example.a21_04_21_gridview02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        adapter = new MyGridAdapter(this);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        int point = intent.getIntExtra("point",0);
        int position = intent.getIntExtra("position",0);
        adapter.setPoint(point,position);
        adapter.notifyDataSetChanged();
        super.onNewIntent(intent);
    }

}