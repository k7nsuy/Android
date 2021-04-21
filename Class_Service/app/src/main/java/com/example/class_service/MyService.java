package com.example.class_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    final static String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate() 호출");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy() 호출");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG,"onStartCommand() 호출 ");
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
            return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {

        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.i(TAG,"command : " + command + ", name : " + name);

        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(150); // 5초 동안 waiting
            } catch (Exception e) {
                e.printStackTrace();
            }
                Log.d(TAG,"waiting " + i + " seconds");
        }

        Intent showIntent = new Intent(getApplicationContext(),MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| // 새로운 액티비티 작업
                Intent.FLAG_ACTIVITY_SINGLE_TOP| // activity 가 충돌하는 것을 방지
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // clear top => mainActivity 로 돌아가기 전 실행 명령어
        showIntent.putExtra("command",command + " from service");
        showIntent.putExtra("name",name + " from service name");
        startActivity(showIntent);
    }
}