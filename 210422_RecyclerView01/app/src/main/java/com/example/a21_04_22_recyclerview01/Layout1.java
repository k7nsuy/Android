package com.example.a21_04_22_recyclerview01;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {

    ImageView iv;
    TextView tvName,tvPhone;

    // LinearLayout 은 생성자가 필요.
    public Layout1(Context context) {
        super(context);
        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1,this,true);
        iv = findViewById(R.id.imageView);
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
    }

    public void setIv(int resId) {
        iv.setImageResource(resId);
    }

    public void setTvName(String name) {
        tvName.setText(name);
    }

    public void setTvPhone(String phone) {
        tvPhone.setText(phone);
    }
}
