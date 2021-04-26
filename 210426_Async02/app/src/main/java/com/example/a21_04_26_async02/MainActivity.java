package com.example.a21_04_26_async02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    EditText edText;
    Button btn01,btn02;
//    MainHandler mainHandler;
    Thread thread;
    Handler handler = new Handler();
    int index;
    AnimTask task;

    ArrayList<Drawable> list1 = new ArrayList<Drawable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        list1.add(res.getDrawable(R.drawable.face1));
        list1.add(res.getDrawable(R.drawable.face2));
        list1.add(res.getDrawable(R.drawable.face3));
        list1.add(res.getDrawable(R.drawable.face4));
        list1.add(res.getDrawable(R.drawable.face5));

        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        iv = findViewById(R.id.imageView);
        edText = findViewById(R.id.edText);
//        mainHandler = new MainHandler();

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                thread = new AnimThread();
//                thread.start();
                task = new AnimTask();
                task.execute(edText.getText().toString());
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
            }
        });
    }

//    class MainHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            Bundle bundle = msg.getData();
//            iv.setImageDrawable(list1.get(bundle.getInt("index")));
//        }
//    }

    class AnimTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            index = 0;
            iv.setImageDrawable(list1.get(index));
        }

        @Override
        protected Integer doInBackground(String... strings) {
            Log.i("Main Thread Data " , strings[0]);
            while (true) {
                index++;
                if (index > 4)
                    index = 0;
                publishProgress(index);

                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    break;
                }
            }
            return index;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            iv.setImageDrawable(list1.get(values[0]));
        }

        @Override
        protected void onCancelled() {
            iv.setImageDrawable(list1.get(0));
        }
    }

//    class AnimThread extends Thread {
//        @Override
//        public void run() {
//            int index = 0;
//            for (int i = 0; i < 100; i++) {
//                final  Drawable drawable = list1.get(index);
//                index++;
//                if (index > 4) {
//                    index = 0;
//                }
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        iv.setImageDrawable(drawable);
//                    }
//                });

//                Message msg = mainHandler.obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("index",index);
//                msg.setData(bundle);
//                mainHandler.sendMessage(msg);

//                try {
//                    Thread.sleep(500);
//                }
//
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                    break; // 가장 가까운 반복문을 벗어난다.
//                }
//            }
//        }
//    }

}