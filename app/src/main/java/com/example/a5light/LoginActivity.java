package com.example.a5light;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a5light.constants.Constants;
import com.example.a5light.fcm.FCMMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import pyxis.uzuki.live.richutilskt.impl.F1;

public class LoginActivity extends AppCompatActivity {
    Button btn_signup;
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
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_login = (Button) findViewById(R.id.btn_login);

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_signup:
                //여기서 서버로 이름, 아이디(이메일) ,password 전송
                Intent intent_signup = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent_signup);
                break;

                case R.id.btn_login:
                //여기서 다시 서버로 아이디와 pass word 전송

                //맞으면
                Intent intent_menu = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent_menu);
                //틀리면
                Toast.makeText(this.getApplicationContext(),"아이디 혹은 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();


                break;



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