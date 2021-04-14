package com.example.class01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv,txt01;
    ImageView iv;
    RadioButton rb1,rb2,rb3;

    CheckBox[] chks = new CheckBox[3];
    int[] chkIds = {R.id.chk01,R.id.chk02,R.id.chk03};

    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv.setTextColor(Color.MAGENTA);
        tv.setTextSize(50);

        txt01=findViewById(R.id.txt01);

        for(int i = 0; i < chks.length; i++) {
            final int idx = i;
            chks[i] = findViewById(chkIds[i]);
            chks[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String chkStr = chks[idx].getText().toString();
                    if(isChecked) {
                        str += chkStr;
                    } else {
                        str = str.replace(chkStr,"");
                    }
                    txt01.setText(str);
                }
            });
        }

//        chk1=findViewById(R.id.chk01);
//        chk2=findViewById(R.id.chk02);
//        chk3=findViewById(R.id.chk03);



//        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                String chkStr = chk1.getText().toString();
//                if(isChecked) {
//                    str += chkStr;
//                } else {
//                    str = str.replace(chkStr,"");
//                }
//                txt01.setText(str);
//            }
//        });


//        checkbox는 radiobutton과 달리 직접적으로 체크하고 체크를 해제해야 한다.
//        => CheckedChangeListener를 사용

        rb1=findViewById(R.id.btn01);
        rb2=findViewById(R.id.btn02);
        rb3=findViewById(R.id.btn03);

        iv=findViewById(R.id.img01);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.drawable.img01);
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.drawable.img02);
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(R.drawable.img03);
            }
        });

    }

    public void tvOnclicked(View view) {
        tv.setText("click the textView");
    }

    public void btnOnclicked(View view) {
        Button btn = (Button)view;
        btn.setText("click the button");
    }
}