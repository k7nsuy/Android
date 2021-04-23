package com.example.a21_04_23_animation01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imageView);
    }

    public void mOnClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSize:
                animation = AnimationUtils.loadAnimation(this,R.anim.scale);
                break;

            case R.id.btnTrans:
                animation = AnimationUtils.loadAnimation(this,R.anim.translation);
                break;

            case R.id.btnRotation:
                animation = AnimationUtils.loadAnimation(this,R.anim.rotation);
                break;

            case R.id.btnAlpa:
                animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
                break;
        }
        iv.startAnimation(animation);
    }
}