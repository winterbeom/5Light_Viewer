package com.example.a5light;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("/register")
    Call<JsonObject> register();

    @GET("/photos")
    Call<List<Detect_Data>> getData();

}
