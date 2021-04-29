package com.example.a21_04_29_webconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn1,btn2,btn3;
    TextView tv;
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        tv = findViewById(R.id.textView);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strBno = editText.getText().toString();
                receiveMakeRequest(strBno);
            }
        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMakeRequest();
            }
        });

        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardListMakeRequeset();
            }
        });
    }

    private void receiveMakeRequest(String strBno) {
        String url = "http://10.100.102.15:8099/androidGet?bno="+strBno;
        StringRequest request = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String,String>();
//                params.put("bno",strBno);
                return super.getParams();
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private void sendMakeRequest() {
        String url = "http://10.100.102.15:8099/androidPost";
        StringRequest request = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("title","title1");
                params.put("content","content1");
                params.put("writer","user01");
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    // Spring 에서 List 보내기.
    private void boardListMakeRequeset() {
        String url = "http://10.100.102.15:8099/boardList";
        StringRequest request = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        println(response);
                        process(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println(error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("title","title1");
                params.put("content","content1");
                params.put("writer","user01");
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    // spring 에서 데이터를 가져온다.
    private void process(String response) {
        Gson gson = new Gson();
        Board[] boards = gson.fromJson(response,Board[].class);
        for (int i = 0; i < boards.length; i++) {
            tv.append("bno : " + boards[i].getBno());
            tv.append("title : " + boards[i].getTitle());
            tv.append("content : " + boards[i].getContent());
            tv.append("writer : " + boards[i].getWriter());
            tv.append("post_date : " + boards[i].getPost_date()+"\n");
        }
    }

    private void println(String str) {
        tv.append(str + "\n");
    }
}