package com.example.class_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("암시적 인텐트");
    }

    public void onBtnClicked(View view) {
        Uri uri = null;
        Intent intent = null;
        switch (view.getId()) {

            case R.id.btnDial:
                uri = Uri.parse("tel:01012345678");
                intent = new Intent(Intent.ACTION_DIAL,uri);
                break;

            case R.id.btnWeb:
                uri = Uri.parse("http://m.naver.com");
                intent = new Intent(Intent.ACTION_VIEW,uri);
                break;

            case R.id.btnGoogle:
                uri = Uri.parse("http://maps.google.com/maps?q"+35.3959361+" , "+128.7384361 );
                intent = new Intent(Intent.ACTION_VIEW,uri);
                break;

            case R.id.btnSearch:
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"안드로이드");
                break;

            case R.id.btnSms:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms body","안녕하세요");
                intent.setData(Uri.parse("smsto:"+Uri.encode("010-1234-5678")));
                break;

            case R.id.btnPhoto:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                break;
        }
        startActivity(intent);
    }

}