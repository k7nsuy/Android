package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnPre, btnNext;
    ImageView[] iv = new ImageView[3];
    int[] ids = {R.id.img01,R.id.img02,R.id.img03};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNext = findViewById(R.id.btnNext);
        btnPre = findViewById(R.id.btnPre);

        for (int i=0; i<iv.length; i++) {
            iv[i] = findViewById(ids[i]);
            if (i != 0) {
                iv[i].setVisibility(View.INVISIBLE);
            }
        }

            btnNext.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (i < iv.length-1 ) {
                        i++;
                        iv[i].setVisibility(View.VISIBLE);
                    }
                }
            });

            btnPre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i > 0 ) {
                        iv[i].setVisibility(View.INVISIBLE);
                        i--;
                    }
                }
            });
        }
    }

