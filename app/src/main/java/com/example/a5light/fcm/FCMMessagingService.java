package com.example.a5light.fcm;


import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a5light.Constants.Constants;
import com.example.a5light.notification.NotificationTool;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class FCMMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d("FCM", String.format("getToken: %s", token));
        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        editor.putString(Constants.FCM_KEY, token);
        editor.commit();
    }
    
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().isEmpty()) {
            return;
        }

        String title = remoteMessage.getData().get("deviceId");
        String content = remoteMessage.getData().get("body");
        String extra = remoteMessage.getData().get("extra");

        Map<String, Object> extrasMap = new HashMap<>();

        String imagePath = "";
        if (!TextUtils.isEmpty(imagePath)) {
            NotificationTool.downloadBitmap(imagePath, (bitmap) -> NotificationTool.show(this, content, title, bitmap, extrasMap));
        } else {
            NotificationTool.show(this, content, title, extrasMap);
        }
    }
}