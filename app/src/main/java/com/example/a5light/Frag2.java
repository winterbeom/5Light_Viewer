package com.example.a5light;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
        Log.e("test","test");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_menu2,container,false);
            Context context = view.getContext();
            recyclerView = view.findViewById(R.id.scrollview);
            recyclerView.setHasFixedSize(true);
            dataList  = new ArrayList<>();
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);



        RetrofitService retrofitService = ApiClient.getClient().create(RetrofitService.class);
        Call<List<Detect_Data>> call = retrofitService.getData();
        call.enqueue(new Callback<List<Detect_Data>>(){


            @Override
            public void onResponse(Call<List<Detect_Data>> call, Response<List<Detect_Data>> response) {

                dataList = response.body();
                dataList = dataList.subList(0,10);
                recyclerAdapter = new RecyclerAdapter(dataList);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<Detect_Data>> call, Throwable t) {

            }
        });


        return view;
    }












}
