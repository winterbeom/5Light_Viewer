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
import com.example.a5light.data.UserData;
import com.example.a5light.fcm.FCMMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import pyxis.uzuki.live.richutilskt.impl.F1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_signup;
    Button btn_login;
    EditText login_email;
    EditText login_passwd;
    String email;
    String passwd;

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
        login_email = (EditText)findViewById(R.id.login_email);
        login_passwd = (EditText)findViewById(R.id.login_passwd);

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
                email = login_email.getText().toString();
                passwd = login_passwd.getText().toString();
                UserData userData = new UserData();
                userData.setEmail(email);
                userData.setPassword(passwd);
                userData.setName("");
                RetrofitService retrofitService = ApiClient.getClient().create(RetrofitService.class);
                Call<JsonObject> call = retrofitService.login(userData);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            int a = 1;
//                         int a= response.body()

                        Toast.makeText(getApplicationContext(),  Integer.toString(a)+"수신 성공", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),  "수신 실패", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    public void getToken() {
        FCMMessagingService.getToken(token -> {
            Log.e(this.getClass().getSimpleName(), token);
        });
    }

    public void saveUserId(int userId) {
        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(Constants.USER_ID_KEY, userId);
        editor.apply();
    }

}