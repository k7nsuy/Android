package com.example.class_event03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    View view,view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);
        view=findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float curX = event.getX();
        float curY = event.getY();
        if(action == MotionEvent.ACTION_DOWN) {
            println("손가락 눌림 : " + curX + ", " + curY);
        } else if (action == MotionEvent.ACTION_MOVE) {
            println("손가락 움직임 : " + curX + ", " + curY);
        } else if (action == MotionEvent.ACTION_UP) {
            println("손가락 뗌 : " + curX + ", " + curY);
        }
        return true;
    }

    private  void println(String str) {
        tv.append(str+"\n");
    }
}