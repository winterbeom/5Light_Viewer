package com.example.a5light;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a5light.data.UserData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText signup_name;
    EditText signup_id;
    EditText signup_passwd;
    String name;
    String id;
    String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup_name = (EditText) findViewById(R.id.signup_name);
        signup_id = (EditText) findViewById(R.id.signup_id);
        signup_passwd = (EditText) findViewById(R.id.signup_password);

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.signup_finish:
                //여기서 서버로 아이디(이메일), 이름 , passwd 전송
                name = signup_name.getText().toString();
                id = signup_id.getText().toString();
                passwd = signup_passwd.getText().toString();

                //이후 다시 로그인 페이지로 전환
                UserData userData = new UserData();
                userData.setName(name);
                userData.setEmail(id);
                userData.setPassword(passwd);
                RetrofitService retrofitService = ApiClient.getClient().create(RetrofitService.class);
                Call<JsonObject> call = retrofitService.register(userData);
                call.enqueue(new Callback<JsonObject>(){

                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (true) {
                            finish();
                        } else {
                            showToast("회원가입에 실패했습니다.");
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        showToast("회원가입에 실패했습니다.");
                    }
                });
                break;
        }
    }


    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}