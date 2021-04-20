package com.example.class_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.i(TAG,"onReceive() 메소드 호출");
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if (messages != null && messages.length > 0) {
            String sender = messages[0].getOriginatingAddress();
            String contents = messages[0].getMessageBody();
            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.i(TAG,sender);
            Log.i(TAG,contents);
            Log.i(TAG,"SMS received date : " + receivedDate);
        }
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objects = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objects.length];
        for (int i = 0; i < messages.length; i++ ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
            }
        }
        return messages;
    }
}