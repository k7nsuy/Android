package com.example.project001;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class layout extends LinearLayout {

    TextView tv1;
    ImageView iv1;

    public layout(Context context) {
        super(context);
        init(context);
    }

    public layout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout,this,true);
        tv1 = findViewById(R.id.tv1);
        iv1 = findViewById(R.id.iv1);

    }

    public void setIv1(int resId) {
        iv1.setImageResource(resId);
    }

    public void setTv1(String name) {
        tv1.setText(name);
    }
}
