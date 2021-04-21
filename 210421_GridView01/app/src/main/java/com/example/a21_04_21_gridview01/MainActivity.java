package com.example.a21_04_21_gridview01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("최신영화");
        gridView = findViewById(R.id.gridView);

        MyGridAdapter adapter = new MyGridAdapter(this);
        gridView.setAdapter(adapter);
    }

    public class MyGridAdapter extends BaseAdapter {

        Context context;
        String title[] = {"영화1","영화2","영화3","영화4","영화5","영화6","영화7","영화8","영화10",
                "영화11","영화12","영화13","영화14","영화15","영화16","영화17","영화18","영화19","영화20"};
        int[] imgIds = {R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,
                R.drawable.mov11,R.drawable.mov12,R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,
                R.drawable.mov16,R.drawable.mov17,R.drawable.mov18,R.drawable.mov19,R.drawable.mov20};

        public MyGridAdapter(Context context) {
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
            // view 크기 조절
            iv.setLayoutParams(new GridView.LayoutParams(300,400));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(5,5,5,5);
            iv.setImageResource(imgIds[position]);

            final int pos = position;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dlgView = View.inflate(getApplicationContext(),
                            R.layout.dialog_layout,null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    TextView tv = dlgView.findViewById(R.id.tvTitle);
                    ImageView iv = dlgView.findViewById(R.id.ivPoster);
                    tv.setText(title[pos]);

                    iv.setImageResource(imgIds[pos]);

                    dlg.setTitle("큰 포스터");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setView(dlgView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });

            return iv;
        }
    }
}