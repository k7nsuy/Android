package com.example.a21_04_23_viewpager01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager1);
        CustomerAdapter adapter = new CustomerAdapter(getLayoutInflater());
        viewPager.setAdapter(adapter);
    }

    public void mOnClicked(View view) {
        int position;
        switch (view.getId()) {
            case R.id.btnPre:
                position = viewPager.getCurrentItem();
                viewPager.setCurrentItem(position - 1,true);
                break;

            case R.id.btnNext:
                position = viewPager.getCurrentItem();
                viewPager.setCurrentItem(position + 1,true);
                break;
        }
    }
}