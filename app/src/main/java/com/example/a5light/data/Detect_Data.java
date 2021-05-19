package com.example.a5light.data;

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

public class Detect_Data {

    @SerializedName("thumbnailUrl")
    private String detect_thumbnail;
@SerializedName("title")
    private String detect_name;
@SerializedName("url")
    private String detect_date;

    public String getDetect_thumbnail() {
        return detect_thumbnail;
    }

    public void setDetect_thumbnail(String detect_thumbnail) {
        this.detect_thumbnail = detect_thumbnail;
    }

    public String getDetect_name() {
        return detect_name;
    }

    public void setDetect_name(String detect_name) {
        this.detect_name = detect_name;
    }

    public String getDetect_date() {
        return detect_date;
    }

    public void setDetect_date(String detect_date) {
        this.detect_date = detect_date;
    }

}
