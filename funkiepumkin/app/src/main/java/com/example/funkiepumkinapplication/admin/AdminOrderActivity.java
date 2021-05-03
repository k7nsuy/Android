package com.example.funkiepumkinapplication.admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.cart.Cart;
import com.example.funkiepumkinapplication.cart.CartAdapter;
import com.example.funkiepumkinapplication.order.Order;
import com.example.funkiepumkinapplication.product.Product;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdminOrderAdapter adapter;
    static RequestQueue requestQueue;
    View dialogView;
    TextView tvDgOrderId, tvDgMemberId, tvDgOrderAddr, tvDgOrderTel, tvDgOrderAmount, tvDgOrderStatus, tvDgOrderDate;
    Button btnDgOrderCp;
    String orderId;
    String status;
    int memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);

        recyclerView = findViewById(R.id.adminOrderRecyclerView);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdminOrderAdapter();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("OrderList");
        actionBar.setDisplayHomeAsUpEnabled(true);


        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        orderListRequest();

        adapter.setOnItemClickListener(new OnAdminOrderClickListener() {
            @Override
            public void onItemClick(AdminOrderAdapter.ViewHolder holder, View view, int position) {
                Order order = adapter.getItem(position);
                dialogView = View.inflate(AdminOrderActivity.this, R.layout.admin_order_dialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(AdminOrderActivity.this);

                tvDgOrderId = dialogView.findViewById(R.id.tvDgOrderId);
                tvDgMemberId = dialogView.findViewById(R.id.tvDgMemberId);
                tvDgOrderAddr = dialogView.findViewById(R.id.tvDgOrderAddr);
                tvDgOrderTel = dialogView.findViewById(R.id.tvDgOrderTel);
                tvDgOrderAmount = dialogView.findViewById(R.id.tvDgOrderAmount);
                tvDgOrderStatus = dialogView.findViewById(R.id.tvDgOrderStatus);
                tvDgOrderDate = dialogView.findViewById(R.id.tvDgOrderDate);
                btnDgOrderCp = dialogView.findViewById(R.id.btnDgOrderCp);

                tvDgOrderId.setText("주문번호: "+order.getOrderId());
                tvDgMemberId.setText("회원코드: "+order.getMemberId());
                tvDgOrderAddr.setText("배송주소: "+order.getOrderAddr());
                tvDgOrderTel.setText("연락처: "+order.getOrderPhone());
                tvDgOrderStatus.setText("상태: "+order.getStatus());
                tvDgOrderDate.setText("주문일: "+order.getOrderDate());
                tvDgOrderAmount.setText("총가격: "+order.getTotalOrderAmount());

                if(order.getStatus().equals("배송대기") || order.getStatus().equals("픽업대기")){
                    btnDgOrderCp.setVisibility(View.VISIBLE);
                }
                btnDgOrderCp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //주문완료
                        orderId = order.getOrderId();
                        status = order.getStatus();
                        memberId = order.getMemberId();
                        orderCompletedRequest();
                    }
                });

                dlg.setTitle("주문확인");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인",null);
                dlg.show();

            }
        });

    }

    public void orderCompletedRequest(){
        String url="http://175.215.100.167:8899/product/orderCompletedBody?orderId=";
        url = url + orderId + "&status="+status + "&memberId="+memberId;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"완료처리되었습니다",Toast.LENGTH_LONG).show();
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


    public void orderListRequest(){
        String url="http://175.215.100.167:8899/product/orderListBody";
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
        Order[] orders = gson.fromJson(response, Order[].class);


        for(int i=0; i<orders.length; i++){
            adapter.addItem(new Order(orders[i].getOrderId(),orders[i].getMemberId(),orders[i].getStatus(),orders[i].getOrderAddr(),
                    orders[i].getOrderPhone(),orders[i].getTotalOrderAmount(),orders[i].getOrderDate()));
            recyclerView.setAdapter(adapter);
        }
    }
}