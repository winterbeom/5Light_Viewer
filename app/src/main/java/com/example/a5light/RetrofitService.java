package com.example.a5light;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("/photos")
    Call<List<Detect_Data>> getData();
}
