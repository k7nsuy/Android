package com.example.funkiepumkinapplication.member;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "4d21f74eebd7c767c66a99e7b4a4a111");
    }
}
