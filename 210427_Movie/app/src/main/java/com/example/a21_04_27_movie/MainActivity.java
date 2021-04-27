package com.example.a21_04_27_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieAdapter adapter;
    MovieDBHelper helper;
    SQLiteDatabase database;
    Button btnMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView1);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter();
        helper = new MovieDBHelper(this);

        btnMovie = findViewById(R.id.btnMovie);
        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MoiveActivity.class);
                startActivity(intent);
            }
        });
    }

    // 다른 activity 에서 실행하고 변경된 db 를 가져오려면 다시 새로 restart 해 주어야 현재 activity 에서
    // 적용이 된다.
    @Override
    protected void onStart() {
        super.onStart();
        database = helper.getReadableDatabase();
        ArrayList<Movie> items = new ArrayList<Movie>();
        String sql = "SELECT * FROM " + helper.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql,null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            int id = cursor.getInt(0);
            int resId = cursor.getInt(1);
            String title = cursor.getString(2);
            String director = cursor.getString(3);
            String actor = cursor.getString(4);
            double point = cursor.getDouble(5);

            Movie item = new Movie(id,resId,title,director,actor,point);
            items.add(item);
        }
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
    }
}