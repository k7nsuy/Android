package com.example.a2021_04_21_listview02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView1);
        adapter = new MyListAdapter(this);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    public class MyListAdapter extends BaseAdapter {

        Context context;

        public MyListAdapter(Context context) {
            this.context = context;
        }

        String[] title = {"토이","호빗","제이슨 본","반지의 제왕",
        "정직한 후보","나쁜녀석들","겨울왕국2","알라딘","극한직업","스파이더맨"};

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(context);
            tv.setText(title[position]);
            tv.setLayoutParams(new ViewGroup.LayoutParams(500,150));
            tv.setTextSize(25);
            tv.setPadding(10,10,10,10);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),
                            PosterActivity.class);
                    intent.putExtra("title",title[position]);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });

            return tv;
        }
    }
}