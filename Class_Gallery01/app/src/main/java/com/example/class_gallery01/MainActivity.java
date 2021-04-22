package com.example.class_gallery01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Gallery g;
    MyGalleryAdapter adapter;
    ImageView iv;

    int[] imgIds = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,
            R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,
            R.drawable.mov09,R.drawable.mov10,R.drawable.mov11,R.drawable.mov12};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("갤러리 영화 포스터");

        g = findViewById(R.id.gallery01);
        iv = findViewById(R.id.iv01);

        adapter = new MyGalleryAdapter(this);
        g.setAdapter(adapter);
        g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                iv.setImageResource(imgIds[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class MyGalleryAdapter extends BaseAdapter {

        Context context;

        public MyGalleryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return imgIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView iv = new ImageView(context);
            iv.setLayoutParams(new Gallery.LayoutParams(200,200));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(5,5,5,5);
            iv.setImageResource(imgIds[position]);

            return iv;
        }
    }
}