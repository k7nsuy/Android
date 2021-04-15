package com.example.class_event01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// activity클래스에 직접 구현
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // button은 btnOnClicked의 view에서 자동으로 변수의 역할을 하기 때문에 변수 생성x
    // xml에서 onClick을 지정 하고 지정 함수를 사용 할 경우만.
    EditText eText01,eText02;
    TextView tView;

    Button[] btns = new Button[4];
    int[] btnIds = {R.id.btnAdd,R.id.btnSub,
            R.id.btnMul,R.id.btnDiv};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText01 = findViewById(R.id.eText01);
        eText02 = findViewById(R.id.eText02);
        tView = findViewById(R.id.tView01);

//        MyListener listener = new MyListener();

        for(int i = 0; i < btns.length; i++) {
            btns[i] = findViewById(btnIds[i]);
            btns[i].setOnClickListener(this);
        }

    }
    // MainActivity 에서 직접 implements View.OnClickListener 해서 자기자신을 setOnClickListener하여
    // 사용
    @Override
    public void onClick(View v) {

        int num01 = Integer.parseInt(eText01.getText().toString());
        int num02 = Integer.parseInt(eText02.getText().toString());
        int result = 0;

        switch (v.getId()) {
            case R.id.btnAdd:
                result = num01 + num02;
                break;
            case R.id.btnSub:
                result = num01 - num02;
                break;
            case R.id.btnMul:
                result = num01 * num02;
                break;
            case R.id.btnDiv:
                result = num01 / num02;
                break;
        }
        tView.setText(result+"");
    }
    // class를 만들어 xml onClick 없이 View.OnClickListener를 사용할 때
//    class MyListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//
//        int num01 = Integer.parseInt(eText01.getText().toString());
//        int num02 = Integer.parseInt(eText02.getText().toString());
//        int result = 0;
//
//            switch (v.getId()) {
//                case R.id.btnAdd:
//                    result = num01 + num02;
//                    break;
//                case R.id.btnSub:
//                    result = num01 - num02;
//                    break;
//                case R.id.btnMul:
//                    result = num01 * num02;
//                    break;
//                case R.id.btnDiv:
//                    result = num01 / num02;
//                    break;
//             }
//              tView.setText(result+"");
//        }
//    }
}

//    public void btnOnClicked(View view) {
//        int num01 = Integer.parseInt(eText01.getText().toString());
//        int num02 = Integer.parseInt(eText02.getText().toString());
//        int result = 0;
//            switch (view.getId()) {
//                case R.id.btnAdd:
//                    result = num01 + num02;
//                    break;
//                case R.id.btnSub:
//                    result = num01 - num02;
//                    break;
//                case R.id.btnMul:
//                    result = num01 * num02;
//                    break;
//                case R.id.btnDiv:
//                    result = num01 / num02;
//                    break;
//        }
//        tView.setText(result+"");
//    }
