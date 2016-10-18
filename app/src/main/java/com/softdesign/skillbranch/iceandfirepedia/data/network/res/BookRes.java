package com.softdesign.skillbranch.iceandfirepedia.data.network.res;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookRes {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}