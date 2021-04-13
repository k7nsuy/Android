package com.example.test01app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        안드로이드 보여지는 화면 ContentView(ApplicationContext => 나를 보여주는 context)를 R(res).layout.activity_main.xml 를 실행한다.
//        R. => resources(layout,drawable 등등)
    }

    public void btnOnclicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Toast.makeText(getApplicationContext(),
                "안드로이드 버튼 클릭",
                Toast.LENGTH_LONG).show();
                break;
            case R.id.button1:
                Toast.makeText(getApplicationContext(),
                        "ios 버튼 클릭",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}