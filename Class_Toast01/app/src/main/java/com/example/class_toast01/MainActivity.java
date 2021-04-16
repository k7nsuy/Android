package com.example.class_toast01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText edNum1,edNum2;
    TextView tv;
    ProgressBar pBar;
    Button btn3_1,btn3_2;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // memory에 저장하는 역할(instance)과 view를 보여주는 역할

        int i = 0;

        edNum1 = findViewById(R.id.edNum1);
        edNum2 = findViewById(R.id.edNum2);
        tv = findViewById(R.id.tv1);
        pBar = findViewById(R.id.pBar);
        pBar.setIndeterminate(false);
        pBar.setProgress(50);
//        while ( i <= 100) {
//            i += 10;
//            pBar.setProgress(i);
//            try {
//                Thread.sleep(500);
//            } catch (Exception e) {
//
//            }
//        }
    }

    public void onButtonPro(View view) {
        switch (view.getId()) {
            case R.id.btn3_1:
                pDialog = new ProgressDialog(this);
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.setMessage("데이터를 확인하는 중입니다.");
                pDialog.show();
                break;
            case R.id.btn3_2:
                if( pDialog != null) {
                    pDialog.dismiss();
                }
        }
    }

    public void onDialogBtnClicked(View view) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("안내");
        dlg.setMessage("정말 종료할래용?");
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText("예 버튼을 눌렀습니다.");
            }
        });
        dlg.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText("아니오 버튼을 눌렀습니다.");
            }
        });
        dlg.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText("취소 버튼을 눌렀습니다.");
            }
        });
        AlertDialog dialog = dlg.create();
        dialog.show();
    }

    public void onButton1Clicked(View view) {

        Snackbar.make(view,"스낵바입니다.",Snackbar.LENGTH_LONG).show();

        // layout을 instance화 하기 위해서 LayoutInflater를 사용
//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.toastboarder,
//                findViewById(R.id.toast_layout));
//        TextView tv = layout.findViewById(R.id.tv1);
//        Toast toast = new Toast(this);
//        tv.setText("모양바꾼 토스트");
//        toast.setGravity(Gravity.CENTER,0,-100);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(layout);
//        toast.show();

//        try {
//            Toast toastView = Toast.makeText(this,
//                    "Toast Message that is moved to",
//                    Toast.LENGTH_SHORT); //MainActivity 객체를 갖고온다.
//            int xOffset = Integer.parseInt(edNum1.getText().toString());
//            int yOffset = Integer.parseInt(edNum2.getText().toString());
//            toastView.setGravity(Gravity.TOP|Gravity.TOP,
//                    xOffset,yOffset);
//            toastView.show();
//        }
//        catch (NumberFormatException e) {
//            e.printStackTrace();
//        }

    }

}