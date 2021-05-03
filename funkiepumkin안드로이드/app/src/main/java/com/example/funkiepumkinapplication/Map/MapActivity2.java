package com.example.funkiepumkinapplication.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.event.EventActivity;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.refrige.RefrigeActivity;
import com.example.funkiepumkinapplication.stamp.StampActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapActivity2 extends AppCompatActivity implements OnMapReadyCallback {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MapAdapter adapter;
    static RequestQueue requestQueue;

    //네이버맵
    private static final int ACCESS_LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private List<Marker> markerList = new ArrayList<Marker>();

    //하단바
    private BottomNavigationView bottomNavigationView;
    //로그인 플로팅버튼
    FloatingActionButton fab;
    int memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Map");
        actionBar.setDisplayHomeAsUpEnabled(true);


        //로그인유지
        Intent memberIntent = getIntent();
        memberId = memberIntent.getIntExtra("memberId",0);

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


        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        recyclerView = findViewById(R.id.rv_2);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MapAdapter();

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }

        //MapListRequest();

        //요기 수정하시면 되어요 (일단 칼럼 세개만 받아오게함)
        adapter.addItem(new Shop("펑키펌킨 동대문점", "주소","010-1111-1111"));
        adapter.addItem(new Shop("펑키펌킨 광안점","주소","010-1111-1111"));
        adapter.addItem(new Shop("펑키펌킨 서면점","주소","010-1111-1111"));
        adapter.addItem(new Shop("펑키펌킨 덕천점","주소","010-1111-1111"));

        recyclerView.setAdapter(adapter);


    }
//    public void MapListRequest(){
//        String url="http://192.168.75.44:8899/shop/shopListBody";
//        StringRequest request = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        processResponse(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.i("결과", error.getMessage());
//                    }
//                }
//        ){
//            protected Map<String,String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String,String>();
//                return params;
//            }
//        };
//        request.setShouldCache(false);
//        requestQueue.add(request);
//    }
//
//    private void processResponse(String response){
//        Gson gson = new Gson();
//        Shop[] shops = gson.fromJson(response, Shop[].class);
//        Log.i("shops", String.valueOf(shops));
//
//        for(int i=0; i<shops.length; i++){
//            adapter.addItem(new Shop(shops[i].getShopId(),shops[i].getShopName(),shops[i].getShopAddress(),
//                    shops[i].getShopTel(),shops[i].getShopLat(),shops[i].getShopLng()));
//            recyclerView.setAdapter(adapter);
//
//            //네이버 맵 마커
//            Marker marker = new Marker();
//            marker.setPosition(new LatLng(Double.parseDouble(shops[i].getShopLat()), Double.parseDouble(shops[i].getShopLng())));
//            //marker.setPosition(new LatLng(shops[i].getShopLat(), shops[i].getShopLng()));
//            marker.setIcon(OverlayImage.fromResource(R.drawable.ic_baseline_location_red));
//            marker.setAnchor(new PointF(0.5f, 1.0f));
//            marker.setMap(naverMap);
//            markerList.add(marker);
//        }
//    }

    //현재 위치를 찾아주는 코드
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        locationSource = new FusedLocationSource(this, ACCESS_LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.setLocationSource(locationSource);
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);

//        naverMap.addOnCameraChangeListener((NaverMap.OnCameraChangeListener) this);
//        naverMap.addOnCameraIdleListener((NaverMap.OnCameraIdleListener) this);
//
//        LatLng mapCenter = naverMap.getCameraPosition().target;
        //fetchStoreSale(mapCenter.latitude, mapCenter.longitude);
    }



    //현재위치 찾는 버튼 권한 설정하는 코드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case ACCESS_LOCATION_PERMISSION_REQUEST_CODE:
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }
    }
}