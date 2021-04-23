package com.example.a21_04_23_slidingani01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Animation leftAni,rightAni;
    LinearLayout slidingPage;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        slidingPage = findViewById(R.id.slidingPage01);

        leftAni = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        rightAni = AnimationUtils.loadAnimation(this,R.anim.translate_right);

        slidingPage.setVisibility(View.INVISIBLE);

        slidingPageAnimationListener listener = new slidingPageAnimationListener();
        leftAni.setAnimationListener(listener);
        rightAni.setAnimationListener(listener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen == true) {
                    slidingPage.startAnimation(leftAni);
                } else {
                    slidingPage.setVisibility(View.VISIBLE);
                    slidingPage.startAnimation(rightAni);
                }
            }
        });
    }

    class slidingPageAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isOpen) {
                slidingPage.setVisibility(View.INVISIBLE);
                btn.setText("Open");
                isOpen = false;
            } else {
                btn.setText("Close");
                isOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}