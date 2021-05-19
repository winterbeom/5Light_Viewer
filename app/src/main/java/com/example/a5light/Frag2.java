package com.example.a5light;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5light.constants.Constants;
import com.example.a5light.data.Detect_Data;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag2 extends Fragment {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter = null;
    List<Detect_Data> dataList;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("test", "test");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.scrollview);
        recyclerView.setHasFixedSize(true);
        dataList = new ArrayList<>();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //리사이클러뷰 구분선 &아이템 간격 설정
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        RecyclerViewDecoration spaceDecoration = new RecyclerViewDecoration(20);
        recyclerView.addItemDecoration(spaceDecoration);


        RetrofitService retrofitService = ApiClient.getClient().create(RetrofitService.class);
        Call<JsonObject> call = retrofitService.getData(Integer.toString(getUserId()));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Gson gson = new Gson();
                    JsonArray jsonArray = response.body().getAsJsonArray("user");
                    Detect_Data[] detect_data_arr = gson.fromJson(jsonArray,Detect_Data[].class);
                    List<Detect_Data> detect_data = Arrays.asList(detect_data_arr);
                    recyclerAdapter = new RecyclerAdapter(detect_data);
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
//        call.enqueue(new Callback<List<Detect_Data>>() {
//
//
//            @Override
//            public void onResponse(Call<List<Detect_Data>> call, Response<List<Detect_Data>> response) {
//
//                dataList = response.body();
//                dataList = dataList.subList(0, 10);
//                recyclerAdapter = new RecyclerAdapter(dataList);
//                recyclerView.setAdapter(recyclerAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Detect_Data>> call, Throwable t) {
//
//            }
//        });


        return view;
    }


    public int getUserId() {
        SharedPreferences pref = requireActivity().getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);
        return pref.getInt(Constants.USER_ID_KEY, -1);
    }


}
