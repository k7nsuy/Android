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

        adapter.addItem(new MovieDetail("영화1","감독1","배우1",
                R.drawable.mov01));
        adapter.addItem(new MovieDetail("영화2","감독2","배우2",
                R.drawable.mov02));
        adapter.addItem(new MovieDetail("영화3","감독3","배우3",
                R.drawable.mov03));
        adapter.addItem(new MovieDetail("영화4","감독4","배우4",
                R.drawable.mov04));
        adapter.addItem(new MovieDetail("영화5","감독5","배우5",
                R.drawable.mov05));
        adapter.addItem(new MovieDetail("영화6","감독6","배우6",
                R.drawable.mov06));
        adapter.addItem(new MovieDetail("영화7","감독7","배우7",
                R.drawable.mov07));
        adapter.addItem(new MovieDetail("영화8","감독8","배우8",
                R.drawable.mov08));
        adapter.addItem(new MovieDetail("영화9","감독9","배우9",
                R.drawable.mov09));
        

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnPosterClickListener() {
            @Override
            public void onItemClick(MovieDetailAdapter.ViewHolder holder, View view, int position) {
                MovieDetail item = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(),MovieActivity.class);
                intent.putExtra("actor",item.getActor());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("director",item.getDirector());
                intent.putExtra("imgIds",item.getImgIds());
                startActivity(intent);
            }
        });


    }
}