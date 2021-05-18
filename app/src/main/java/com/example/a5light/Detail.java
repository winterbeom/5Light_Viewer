package com.example.a5light;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

public class Detail extends AppCompatActivity {
    private Intent intent;
    private TextView detail_name;
    private TextView detail_date;
    private ImageView detail_thumbnail;


    private int number;
    private String name;
    private String date;
    private String thumbnail;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        intent = getIntent();
        number = intent.getIntExtra("number", 0);
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        thumbnail = intent.getStringExtra("url");

        detail_date = findViewById(R.id.detail_date);
        detail_name = findViewById(R.id.detail_name);
        detail_thumbnail = findViewById(R.id.detail_thumbnail);

        detail_name.setText("이름"+name);
        detail_date.setText("날짜"+date);

        GlideUrl url = new GlideUrl(thumbnail,new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());

        Glide. with(detail_thumbnail.getContext())
                .load(url)
                .error(R.drawable.ic_launcher_foreground)
                .into(detail_thumbnail);

    }


}