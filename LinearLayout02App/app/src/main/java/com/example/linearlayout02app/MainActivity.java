package com.example.linearlayout02app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    Button btn1, btn2;

    EditText edNum1 , edNum2;
    TextView tv;

//    Button[] btns = new Button[4];
//    int[] ids = {R.id.btnAdd,R.id.btnSub,R.id.btnMul,R.id.btnDiv};
//    Button btnAdd, btnSub, btnMul, btnDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test03);
        edNum1 = findViewById(R.id.edNum1);
        edNum2 = findViewById(R.id.edNum2);
        tv = findViewById(R.id.result);
//        for(int i=0; i<btns.length; i++) {
//            final int idx = i;  // event가 발생 하면 for문의 i가 event에서 쓰일 수 없으므로
//            // final 상수를 통해서 i를 대입하여 idx를 집어 넣는다.
//
//            btns[i] = findViewById(ids[i]);
//            btns[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int num1 = Integer.parseInt(edNum1.getText().toString());
//                    int num2 = Integer.parseInt(edNum2.getText().toString());
//                    int result = 0;
//                    switch (ids[idx]) {
//                        case R.id.btnAdd:
//                            result = num1 + num2;
//                            break;
//                        case R.id.btnSub:
//                            result = num1 - num2;
//                            break;
//                        case R.id.btnMul:
//                            result = num1 * num2;
//                            break;
//                        case R.id.btnDiv:
//                            result = num1 / num2;
//                            break;
//                    }
//                    tv.setText(result+"");
//                }
//            });
        }

        public void onClicked(View view) {
                int num1 = Integer.parseInt(edNum1.getText().toString());
                int num2 = Integer.parseInt(edNum2.getText().toString());
                int result = 0;
                    switch (view.getId()) {
                        case R.id.btnAdd:
                            result = num1 + num2;
                            break;
                        case R.id.btnSub:
                            result = num1 - num2;
                            break;
                        case R.id.btnMul:
                            result = num1 * num2;
                            break;
                        case R.id.btnDiv:
                            result = num1 / num2;
                            break;
                    }
                    tv.setText(result+"");

//        btnAdd = findViewById(R.id.btnAdd);
//        btnSub = findViewById(R.id.btnSub);
//        btnMul = findViewById(R.id.btnMul);
//        btnDiv = findViewById(R.id.btnDiv);


//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int num1 = Integer.parseInt(edNum1.getText().toString());
//                int num2 = Integer.parseInt(edNum2.getText().toString());
//                tv.setText(num1+num2+"");
//            }
//        });
    }
}
//        btn1 = findViewById(R.id.button3);
//        btn2 = findViewById(R.id.button4);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),
//                        "You clicked the button1",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),
//                        "You clicked the button2",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}