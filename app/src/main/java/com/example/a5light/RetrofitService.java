package com.example.a5light;

import com.example.a5light.data.Detect_Data;
import com.example.a5light.data.UserData;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("/users/")
    Call<JsonObject> register(@Body UserData userData);

    @POST("/login/")
    Call<String> login(@Body UserData userData);

    @GET("/videos/{mId}")
    Call<JsonObject> getData(@Path("mId") String mId);

    @FormUrlEncoded
    @POST("/token/")
    Call<JsonObject> updateToken(@Field("id") String mId, @Field("token") String token);

}
