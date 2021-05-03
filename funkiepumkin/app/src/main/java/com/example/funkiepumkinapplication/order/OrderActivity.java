package com.example.funkiepumkinapplication.order;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.event.EventActivity;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.member.Member;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.cart.Cart;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OrderAdapter adapter;
    static RequestQueue requestQueue;
    EditText etOrderName,etOrderAddr,etOrderTel,etOrderPoint,etOrderName2,etOrderTel2;
    TextView tvOrderPoint,tvOrderProductPrice,tvOrderShipping,tvOrderDiscount,tvOrderPayment;
    LinearLayout layout1,layout2;
    int tag;
    RadioGroup shopRadioGroup;
    String shop;
    Button btnOrder;
    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        SharedPreferences sf = getSharedPreferences("member",MODE_PRIVATE);
        memberId = sf.getInt("memberId",0);

        ActionBar actionBar = getSupportActionBar();  //제목줄 객체 얻어오기
        actionBar.setTitle("Order");  //액션바 제목설정
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.orderRecyclerView);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter();

        etOrderName = findViewById(R.id.etOrderName);
        etOrderName2 = findViewById(R.id.etOrderName2);
        etOrderAddr = findViewById(R.id.etOrderAddr);
        etOrderTel = findViewById(R.id.etOrderTel);
        etOrderTel2 = findViewById(R.id.etOrderTel2);
        etOrderPoint = findViewById(R.id.etOrderPoint);
        tvOrderPoint = findViewById(R.id.tvOrderPoint);
        tvOrderProductPrice = findViewById(R.id.tvOrderProductPrice);
        tvOrderShipping = findViewById(R.id.tvOrderShipping);
        tvOrderDiscount = findViewById(R.id.tvOrderDiscount);
        tvOrderPayment = findViewById(R.id.tvOrderPayment);
        layout1 = findViewById(R.id.orderLayout1);
        layout2 = findViewById(R.id.orderLayout2);
        shopRadioGroup = findViewById(R.id.shopRadioGroup);
        btnOrder = findViewById(R.id.btnOrder);

        Intent intent = getIntent();

        if(intent.getIntExtra("tag",0)==0){
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            tag=0;
        }else {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            tvOrderShipping.setText(2000+"");
            tag=1;
        }

        shopRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1 :
                        shop = "지점1"; break;
                    case R.id.rb2 :
                        shop = "지점2"; break;
                    case R.id.rb3 :
                        shop = "지점3"; break;
                    case R.id.rb4 :
                        shop = "지점4"; break;
                }
            }
        });

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        cartListRequest();
        memberRequest();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderRequest();

            }
        });

    }

    public void orderRequest(){
        String url="http://175.215.100.167:8899/product/order";
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"주문완료",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplication(), OrderCompletedActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"주문실패",Toast.LENGTH_LONG).show();
                    }
                }
        ){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                if(tag==0){
                    params.put("orderName",etOrderName2.getText().toString());
                    params.put("orderAddr",shop);
                    params.put("orderPhone",etOrderTel2.getText().toString());
                    params.put("status","픽업대기");
                }else{
                    params.put("orderName",etOrderName.getText().toString());
                    params.put("orderAddr",etOrderAddr.getText().toString());
                    params.put("orderPhone",etOrderTel.getText().toString());
                    params.put("status","배송대기");
                }
                params.put("memberId",memberId+"");
                params.put("totalOrderAmount",tvOrderPayment.getText().toString());
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void memberRequest(){
        String url="http://175.215.100.167:8899/product/orderForm?memberId="; //수정하기
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

    public void cartListRequest(){
        String url="http://175.215.100.167:8899/product/cartList?memberId="; //수정하기
        url = url + memberId;
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
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

    private void processResponse(String response){
        Gson gson = new Gson();
        Cart[] carts = gson.fromJson(response, Cart[].class);

        int productsPrice=0;

        for(int i=0; i<carts.length; i++){
            adapter.addItem(new Cart(carts[i].getCartId(),carts[i].getProductId(),carts[i].getCartAmount(),carts[i].getMemberId(),
                    carts[i].getProductName(),carts[i].getProductPrice(),carts[i].getProductImg()));
            recyclerView.setAdapter(adapter);

            productsPrice = productsPrice + carts[i].getProductPrice() * carts[i].getCartAmount();
            tvOrderProductPrice.setText(productsPrice+"");
            tvOrderPayment.setText((Integer.parseInt(tvOrderShipping.getText().toString())+productsPrice)+"");
            //-Integer.parseInt(etOrderPoint.getText().toString())
        }

    }

    private void processResponse2(String response){
        Gson gson = new Gson();
        Member member = gson.fromJson(response, Member.class);

        if(member.getUserName()!=null){
            etOrderName.setText(member.getUserName());
            etOrderName2.setText(member.getUserName());
        }
        if(member.getUserAddress()!=null){
            etOrderAddr.setText(member.getUserAddress());
        }
        if(member.getUserTel()!=null){
            etOrderTel.setText(member.getUserTel());
            etOrderTel2.setText(member.getUserTel());
        }
        tvOrderPoint.setText(member.getPoint()+"");

    }
}