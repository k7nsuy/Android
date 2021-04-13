package com.example.test03app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("tel:010-0000-0000"));
                startActivity(intent2);
                break;
        }
    }
}