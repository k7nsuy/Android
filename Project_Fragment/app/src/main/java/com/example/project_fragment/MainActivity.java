package com.example.project_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            AFragment movieA = new AFragment();
            FragmentManager fmg = getSupportFragmentManager();
            FragmentTransaction ft = fmg.beginTransaction();
            ft.add(R.id.fragment_container, movieA);
            ft.commit();
        }
    }

    public void onBtnClicked(View view) {
        Fragment fr = null;
        switch (view.getId()) {
            case R.id.btnMovie1:
                fr = new AFragment();
                break;

            case R.id.btnMovie2:
                fr = new BFragment();
                break;

            case R.id.btnMovie3:
                fr = new CFragment();
                break;

            case R.id.btnMovie4:
                fr = new DFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fr)
                .addToBackStack(null)
                .commit();
    }
}