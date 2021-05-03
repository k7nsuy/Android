package com.example.funkiepumkinapplication.stamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.cart.Cart;
import com.example.funkiepumkinapplication.event.EventActivity;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class StampActivity extends AppCompatActivity {
    TextView tvStampCount;
    static RequestQueue requestQueue;
    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Stamp");
        actionBar.setDisplayHomeAsUpEnabled(true);

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

        tvStampCount = findViewById(R.id.tvStampCount);

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        stampCountRequest();
    }

    public void stampCountRequest(){
        String url="http://175.215.100.167:8899/product/stampCount?memberId=";
        url = url + memberId;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String count) {
                        tvStampCount.setText(count);
                        stampImg(count);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("결과", error.getMessage());
                    }
                }
        ){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private void stampImg(String count){
        iv1 = findViewById(R.id.stamp1);
        iv2 = findViewById(R.id.stamp2);
        iv3 = findViewById(R.id.stamp3);
        iv4 = findViewById(R.id.stamp4);
        iv5 = findViewById(R.id.stamp5);
        iv6 = findViewById(R.id.stamp6);
        iv7 = findViewById(R.id.stamp7);
        iv8 = findViewById(R.id.stamp8);
        iv9 = findViewById(R.id.stamp9);
        iv10 = findViewById(R.id.stamp10);

        int stamp = Integer.parseInt(count);

        switch(stamp){
            case 1 :
        }
        if(stamp>0){
            iv1.setImageResource(R.drawable.stamp1);
        }if(stamp>1){
            iv2.setImageResource(R.drawable.stamp2);
        }if(stamp>2){
            iv3.setImageResource(R.drawable.stamp3);
        }if(stamp>3){
            iv4.setImageResource(R.drawable.stamp4);
        }if(stamp>4){
            iv5.setImageResource(R.drawable.stamp1);
        }if(stamp>5){
            iv6.setImageResource(R.drawable.stamp2);
        }if(stamp>6){
            iv7.setImageResource(R.drawable.stamp3);
        }if(stamp>7){
            iv8.setImageResource(R.drawable.stamp4);
        }if(stamp>8){
            iv9.setImageResource(R.drawable.stamp1);
        }if(stamp>9){
            iv10.setImageResource(R.drawable.stamp2);
        }
    }

}