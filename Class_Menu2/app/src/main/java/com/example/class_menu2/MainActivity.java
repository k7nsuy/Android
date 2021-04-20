package com.example.class_menu2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("액션바");
        tv = findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu1,menu);
        View v = menu.findItem(R.id.menu_search).getActionView();
        if (v != null) {
            EditText editText = v.findViewById(R.id.edit1);
            if (editText != null) {
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        tv.setText(editText.getText().toString());
                        return false;
                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_refresh:
                tv.setText("새로고침 메뉴 선택");
                break;

            case R.id.menu_search:
                tv.setText("탐색 메뉴 선택");
                break;

            case R.id.menu_settings:
                tv.setText("세팅 메뉴 선택");
                break;
        }

        return true;
    }
}