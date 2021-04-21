package com.example.a21_04_21_gridview02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyGridAdapter extends BaseAdapter {

    Context context;
    TextView tvPoint;

    String titles[] = {"영화1","영화2","영화3","영화4","영화5","영화6","영화7","영화8","영화9","영화10",
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
        ViewGroup rootView = (ViewGroup) ViewGroup.inflate(context,R.layout.sub_layout,null);
        rootView.setPadding(10,10,10,10);
        TextView tvTitle = rootView.findViewById(R.id.tvTitle);
        ImageView ivPoster = rootView.findViewById(R.id.ivPoster);
        tvPoint = rootView.findViewById(R.id.tvPoint);
        tvTitle.setText(titles[position]);
        ivPoster.setImageResource(imgIds[position]);
        tvPoint.setText("평점 : " + 0);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MovieActivity.class);
                intent.putExtra("imgIds",imgIds[position]);
                intent.putExtra("titles",titles[position]);
                context.startActivity(intent);
            }
        });
        return rootView;
    }

    public void setPoint(int point) {
        tvPoint.setText("평점 : " + point);
    }
}
