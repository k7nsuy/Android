package com.example.class_activity4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edit1,edit2;
    RadioGroup raGrp;
    Button button;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        tv = findViewById(R.id.tvRlt);
        raGrp = findViewById(R.id.raGrp);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num1 = Integer.parseInt(edit1.getText().toString());
                int num2 = Integer.parseInt(edit2.getText().toString());
                String op = "";

                switch (raGrp.getCheckedRadioButtonId()) {
                    case R.id.raAdd:
                        op = "+";
                        break;
                    case R.id.raSub:
                        op = "-";
                        break;
                    case R.id.raMul:
                        op = "*";
                        break;
                    case R.id.raDiv:
                        op = "/";
                        break;
                }
                Intent intent = new Intent(getApplicationContext(),CalcActivity.class);
                intent.putExtra("num1",num1);
                intent.putExtra("num2",num2);
                intent.putExtra("op",op);
                startActivityForResult(intent,100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK) {
            tv.setText("계산 결과 : " + data.getIntExtra("sum",0));
        }
    }
}