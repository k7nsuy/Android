package com.example.project001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieDetailAdapter adapter;
    GridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MovieDetailAdapter();
        manager = new GridLayoutManager(this,3);

        adapter.addItem(new MovieDetail("aaa","sss","ddd",
                R.drawable.mov01));
        

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnPosterClickListener() {
            @Override
            public void onItemClick(MovieDetailAdapter.ViewHolder holder, View view, int position) {
                MovieDetail item = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(),MovieActivity.class);
                intent.putExtra("item",item);
                startActivity(intent);
            }
        });


    }
}