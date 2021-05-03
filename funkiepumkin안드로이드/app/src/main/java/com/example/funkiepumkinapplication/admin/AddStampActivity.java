package com.example.funkiepumkinapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.funkiepumkinapplication.MainActivity;
import com.example.funkiepumkinapplication.R;
import com.example.funkiepumkinapplication.member.LoginActivity;
import com.example.funkiepumkinapplication.member.MemberActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

public class AddStampActivity extends AppCompatActivity {
    static RequestQueue requestQueue;
    String memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stamp);

        new IntentIntegrator(this).setOrientationLocked(false).initiateScan();

        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // 결과값 출력
        if(result!=null){
            if(result.getContents()!=null){
                memberId = result.getContents();

                addStampRequest();

            }else {
                Toast.makeText(this,"취소됨",Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);

        }
    }



    public void addStampRequest(){
        String url="http://175.215.100.167:8899/product/addStamp?memberId=";
        url = url + memberId;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
//                        Intent memberIntent = new Intent(AddStampActivity.this, MemberActivity.class);
//                        AddStampActivity.this.startActivity(memberIntent);
                        finish();
                        Toast.makeText(getApplicationContext(),"memberId:"+memberId+"적립완료",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error",error.getMessage());
                        finish();
                        Toast.makeText(getApplicationContext(),"적립실패",Toast.LENGTH_LONG).show();
                        //textView.setText(memberId+"적립실패");
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

}