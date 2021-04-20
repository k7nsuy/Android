package com.example.class_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;
    int scaleX = 1;
    int scaleY = 1;
    int rotate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);

        // button 을 ContextMenu 와 연결
        registerForContextMenu(btn);
        registerForContextMenu(btn2);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if (v == btn) {
            menuInflater.inflate(R.menu.menu1,menu);
        }
        if (v == btn2) {
            menuInflater.inflate(R.menu.menu2,menu);
        }
    }

    // menu 를 mainActivity와 연결
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // menu.xml 불러오기
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu,menu);

        // menu.xml 파일 작성없이 main page 에서 작성하는 방법.
        menu.add(0,1,0,"빨강");
        menu.add(0,2,0,"초록");
        menu.add(0,3,0,"파랑");

        SubMenu subMenu = menu.addSubMenu("버튼모양 변경");
        subMenu.add(0,4,0,"확대");
        subMenu.add(0,5,0,"축소");
        subMenu.add(0,6,0,"회전");

        return true;
    }

    // menu 에 기능 추가하기
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case 1:
                btn.setBackgroundColor(Color.RED);
                break;

            case 2:
                btn.setBackgroundColor(Color.GREEN);
                break;

            case 3:
                btn.setBackgroundColor(Color.BLUE);
                break;

            case R.id.subSize:
                btn.setScaleX(2);
                btn.setScaleY(2);
                break;

            case 4:
                scaleX += 1;
                scaleY += 1;
                btn.setScaleX(scaleX);
                btn.setScaleY(scaleY);
                break;

            case 5:
                scaleX -= 1;
                scaleY -= 1;
                btn.setScaleX(scaleX);
                btn.setScaleY(scaleY);
                break;

            case 6:
                rotate += 15;
                if (rotate >= 360) {
                    rotate = 0;
                }
                btn.setRotation(rotate);
                break;
        }
        return true;
    }
}