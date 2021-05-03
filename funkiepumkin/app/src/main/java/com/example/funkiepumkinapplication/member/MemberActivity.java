package com.example.funkiepumkinapplication.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.ImageLoadTask;
import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.admin.AddStampActivity;
import com.example.funkiepumkinapplication.admin.AdminOrderActivity;
import com.example.funkiepumkinapplication.admin.ResetStampActivity;
import com.example.funkiepumkinapplication.event.EventActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.kakao.sdk.user.UserApiClient;

import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MemberActivity extends AppCompatActivity {
    TextView tvMemberName, tvMemberPoint,tvMemberCoupon;
    ImageView ivQRCode;
    static RequestQueue requestQueue;
    LinearLayout memberLayout, adminLayout;

    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;
    String memberNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        //setContentView(R.layout.nav_header_login);

        tvMemberName = findViewById(R.id.tvMemberName);
        tvMemberPoint = findViewById(R.id.tvMemberPoint);
        tvMemberCoupon = findViewById(R.id.tvMemberCoupon);
        ivQRCode = findViewById(R.id.ivQRCode);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Member");
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sf = getSharedPreferences("member",MODE_PRIVATE);
        memberId = sf.getInt("memberId",0);
        memberNickname = sf.getString("memberNickname", null);


        fab=findViewById(R.id.fab);
        if(memberId>0 || memberNickname!=null) {//로그인 상태일때, memberId를 인텐트로 받아왔을때
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

        adminLayout = findViewById(R.id.adminLayout);
        memberLayout = findViewById(R.id.memberLayout);

        if(memberId==999) {
            adminLayout.setVisibility(View.VISIBLE);
            memberLayout.setVisibility(View.GONE);
        }


        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        memberRequest();
        if(memberNickname!=null){
            tvMemberName.setText(memberNickname+"님");
        }


        Button btn = findViewById(R.id.btnLogout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("member",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("memberId",0);
                //카카오 로그아웃
                editor.putString("memberNickname", null);
                editor.commit();


                Toast.makeText(getApplicationContext(),"로그아웃 되셨습니다",Toast.LENGTH_LONG).show();
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });

        Button btnAddStamp = findViewById(R.id.addStamp);
        btnAddStamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddStampActivity.class);
                startActivity(intent);
            }
        });

        Button btnResetStamp = findViewById(R.id.resetStamp);
        btnResetStamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResetStampActivity.class);
                startActivity(intent);
            }
        });

        Button btnAdminOrder = findViewById(R.id.adminOrder);
        btnAdminOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminOrderActivity.class);
                startActivity(intent);
            }
        });



    }
    public void memberRequest(){
        String url="http://175.215.100.167:8899/product/orderForm?memberId=";
        url = url + memberId;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        processResponse2(response);
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
    private void processResponse2(String response){
        Gson gson = new Gson();
        Member member = gson.fromJson(response, Member.class);
        tvMemberName.setText(member.getUserName() + "님");
        tvMemberPoint.setText(member.getPoint()+" P");


        if(memberId!=999) {
            String url = "http://175.215.100.167:8899/resources/qr_img/";

            ImageLoadTask task = new ImageLoadTask(url + "qr" + member.getMemberId() + ".jpg", ivQRCode);
            task.execute();
        }

    }
}