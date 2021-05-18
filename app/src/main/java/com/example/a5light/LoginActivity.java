package com.example.a5light;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a5light.constants.Constants;
import com.example.a5light.fcm.FCMMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import pyxis.uzuki.live.richutilskt.impl.F1;

public class LoginActivity extends AppCompatActivity {
    Button btn_get;
    Button btn_login;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.InitalizeView();


        getToken();
    }

    public void InitalizeView() {
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_login = (Button) findViewById(R.id.btn_login);
        id = (EditText) findViewById(R.id.idNum);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_get:

                btn_get.setText("재전송");
                id.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_login:
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                break;
            // 다른 화면으로 전환


        }
    }

    public void getToken() {
        FCMMessagingService.getToken(token->{
            Log.e(this.getClass().getSimpleName(),token);
        });
    }

    public void saveUserId(int userId) {
        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(Constants.USER_ID_KEY, userId);
        editor.apply();
    }

}