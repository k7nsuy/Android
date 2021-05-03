package com.example.funkiepumkinapplication.event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventResultActivity extends AppCompatActivity {
    TextView tvResult;

    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_result);

        SharedPreferences sf = getSharedPreferences("member",MODE_PRIVATE);
        memberId = sf.getInt("memberId",0);

        fab=findViewById(R.id.fab);
        if(memberId>0) {//로그인 상태일때, memberId를 인텐트로 받아왔을때
            fab.setVisibility(View.GONE);
        }else{//로그아웃 상태일때, memberId를 인텐트로 받아왔을때

            fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        //하단바
        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_stamp:
                        Intent stampIntent = new Intent(getApplicationContext(), StampActivity.class);
                        stampIntent.putExtra("memberId",memberId);
                        startActivity(stampIntent);
                        finish();
                        break;
                    case R.id.action_home:
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        mainIntent.putExtra("memberId",memberId);
                        startActivity(mainIntent);
                        finish();
                        break;
                    case R.id.action_refridge:
                        Intent refrigeIntent = new Intent(getApplicationContext(), RefrigeActivity.class);
                        refrigeIntent.putExtra("memberId",memberId);
                        startActivity(refrigeIntent);
                        finish();
                        break;
                    case R.id.action_event:
                        Intent eventIntent = new Intent(getApplicationContext(), EventActivity.class);
                        eventIntent.putExtra("memberId",memberId);
                        startActivity(eventIntent);
                        finish();
                        break;
                }
            }
        });



        tvResult=findViewById(R.id.tvResult);

        double randomDouble = Math.random();
        int randomInt = (int) (randomDouble *6)+1;
        tvResult.setText(randomInt+"");

    }
}