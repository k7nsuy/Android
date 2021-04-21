package com.example.calculateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    TextView tvResult;
    Button[] btns=new Button[16];
    String str="";
    int[] btnIds={
            R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,
            R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,
            R.id.btn9,R.id.btn9,R.id.btnAdd,R.id.btnSub,
            R.id.btnMul,R.id.btnDiv,R.id.btnCE,R.id.btnResult
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult=findViewById(R.id.textView);
        for(int i=0; i<btns.length;i++){
            final int idx=i;
            btns[i]=findViewById(btnIds[i]);
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String btnStr=btns[idx].getText().toString();
                   if (btnStr.equals("=")){
                       int result=0;
                       StringTokenizer st=new StringTokenizer(str,"+-*/",true);
                       int num1=Integer.parseInt(st.nextToken());
                       String op=st.nextToken();
                       int num2=Integer.parseInt(st.nextToken());
                       switch (op){
                           case "+":result=num1+num2;break;
                           case "-":result=num1-num2;break;
                           case "*":result=num1*num2;break;
                           case "/":result=num1/num2;break;
                       }
                       str=Integer.toString(result);
                       tvResult.setText(str);
                   }else if(btnStr.equals("CE")){
                       str="";
                       tvResult.setText(str);

                   }else{
                       str+=btns[idx].getText().toString();
                       tvResult.setText(str);
                   }
                }
            });
        }
    }
}