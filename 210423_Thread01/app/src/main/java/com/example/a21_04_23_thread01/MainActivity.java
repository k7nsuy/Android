package com.example.a21_04_23_thread01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value = 0;
    TextView tv;
    Button button,button2;
    MainHandler handler;
    Handler handler1 = new Handler();
    ProcessThread processThread;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        editText = findViewById(R.id.editTextTextPersonName);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                Message message = Message.obtain();
                message.obj = input;
                processThread.processHandler.sendMessage(message);
            }
        });

        processThread = new ProcessThread();
        handler = new MainHandler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new BackgroundThread());
                thread.start();
            }
        });
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            tv.setText("value 값 : " + value);
        }
    }

    // thread 는 run이 있어야 한다.
    class ProcessThread extends Thread {

        ProcessHandler processHandler = new ProcessHandler();

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            Looper.loop();
        }

        class ProcessHandler extends Handler {

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                final String output = msg.obj + " from thread.";

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(output);
                    }
                });
            }
        }
    }


    class BackgroundThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
                    Log.i("Thread","value = " + value);
//                    tv.setText("value : " + value);
                    value++;

                    Message msg = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putInt("value",value);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
        }
    }

//    class  BackgroundThread extends Thread {
//        @Override
//        public void run() {
//            for (int i = 0; i < 30; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    Log.i("Thread","value = " + value);
//                    tv.setText("value : " + value);
//                    value++;
//                }
//            }
//
//            }
//        }
