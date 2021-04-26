package com.example.a21_04_26_async01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    int value = 0;
    ProgressBar progressBar;
    Button button1,button2;
    BackgroundTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new BackgroundTask();
                task.execute(10);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
            }
        });
    }

    // generic 은 객체만 들어 갈 수 있으므로 Integer 사용.
    class BackgroundTask extends AsyncTask<Integer,Integer,Integer> {
        @Override
        protected void onPreExecute() {

            value = 0;
            progressBar.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... values) { // => Integer[] values
            int n = values[0];
            while (isCancelled() == false) {
                value += n;
                if (value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue()); // 객체화 된 Integer 를 int Value 를 통해
            // get 한다.
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setProgress(0);
        }

        @Override
        protected void onCancelled() {
            progressBar.setProgress(0);
        }
    }
}