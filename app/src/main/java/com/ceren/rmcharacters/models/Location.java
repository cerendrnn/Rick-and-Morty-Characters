package com.ceren.rmcharacters.models;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public Location(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String url) {
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Location{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
