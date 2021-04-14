package com.example.exam01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameActivity extends AppCompatActivity {
    Button btn;
    ImageView iv;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        btn=findViewById(R.id.btn01);
        iv=findViewById(R.id.iv02);
        iv.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    flag=false;
                    iv.setVisibility(View.INVISIBLE);
                }else{
                    flag=true;
                    iv.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}