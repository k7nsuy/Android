package com.example.a21_04_28_urlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a21_04_28_urlconnection.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    TextView tv;
    Button btn;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.edText);
        tv=findViewById(R.id.textView);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strUrl=ed.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestPage(strUrl);
                    }
                }).start();

            }
        });
    }
    private void requestPage(String strUrl){
        StringBuilder output=new StringBuilder();
        try{
            URL url=new URL(strUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            if(conn!=null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                int resCode=conn.getResponseCode();
                println("resCode="+resCode);
                if(resCode==200) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while (true) {
                        line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        output.append(line + "\n");
                    }
                    reader.close();
                    conn.disconnect();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            println("예외 발생"+e.toString());
        }
        println("응답->"+output.toString());
    }
    private void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.append(data+"\n");
            }
        });
    }
}