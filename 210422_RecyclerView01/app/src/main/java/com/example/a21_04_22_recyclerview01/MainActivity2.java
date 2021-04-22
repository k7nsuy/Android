package com.example.a21_04_22_recyclerview01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    PersonAdapter adapter;
//    GridLayoutManager gManager;
    LinearLayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView1);
        adapter = new PersonAdapter();
//        gManager = new GridLayoutManager(this,2);
        lManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        adapter.addItem(new Person("김민수","010-0000-0001"));
        adapter.addItem(new Person("손민수","010-0000-0002"));
        adapter.addItem(new Person("박민수","010-0000-0003"));
        adapter.addItem(new Person("정민수","010-0000-0004"));
        adapter.addItem(new Person("나민수","010-0000-0005"));

//        recyclerView.setLayoutManager(gManager);
        recyclerView.setLayoutManager(lManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(),item.getName(),
                        Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(),PersonActivity.class);
//                intent.putExtra("item",item);
//                startActivity(intent);
            }
        });

    }
}