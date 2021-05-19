package com.example.a5light.data;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

public class Detect_Data {

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private String video;

    @SerializedName("object")
    private String object;
    @SerializedName("dateTime")
    private String dateTime;

    public String getThumbnail(){
        return thumbnail;
    }

    public int getUserId(){
        return userId;
    }

    public int getId(){
        return id;
    }

    public String getVideo(){
        return video;
    }

    public String getObject(){
        return object;
    }

    public String getDateTime() {
        return dateTime;
    }
}
