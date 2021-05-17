package com.example.a5light;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a5light.Constants.Constants;
import com.google.firebase.messaging.FirebaseMessaging;

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
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("fcm", "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constants.FCM_KEY, token); // key, value를 이용하여 저장하는 형태
                    editor.commit();
                });
    }

}