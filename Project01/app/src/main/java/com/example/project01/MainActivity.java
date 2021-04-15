package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chk;
    TextView txt2;
    ImageView img;
    RadioGroup rGrp;
    Button rBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진보기");

        chk = findViewById(R.id.chk);
        rGrp = findViewById(R.id.rGrp);

        txt2 = findViewById(R.id.txt2);

        rBtn = findViewById(R.id.rBtn);
        img = findViewById(R.id.img);

        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                view에서 check 클릭시 isChecked가 활성화 된다.
                if(isChecked) {
                    txt2.setVisibility(View.VISIBLE);
                    rGrp.setVisibility(View.VISIBLE);
                    rBtn.setVisibility(View.VISIBLE);
                    img.setVisibility(View.VISIBLE);
                } else {
                    txt2.setVisibility(View.INVISIBLE);
                    rGrp.setVisibility(View.INVISIBLE);
                    rBtn.setVisibility(View.INVISIBLE);
                    img.setVisibility(View.INVISIBLE);
                }
            }
        });

        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rGrp.getCheckedRadioButtonId()) {
                    case R.id.rBtn1:
                        img.setImageResource(R.drawable.img01);
                        break;
                    case R.id.rBtn2:
                        img.setImageResource(R.drawable.img02);
                        break;
                    case R.id.rBtn3:
                        img.setImageResource(R.drawable.img03);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),
                                "이미지를 선택해 주세요",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}