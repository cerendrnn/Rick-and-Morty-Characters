package com.ceren.rmcharacters.models;

import com.google.gson.annotations.SerializedName;

public class Origin {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
