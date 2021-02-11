package com.ceren.rmcharacters.requests.responses;

import android.media.Image;

import com.ceren.rmcharacters.models.Location;
import com.ceren.rmcharacters.models.Origin;
import com.ceren.rmcharacters.models.ShowCharacter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterResponse {

    @SerializedName("id")
    @Expose()
    private int id;

    public int getId(){
        return id;
    }

    @SerializedName("name")
    @Expose()
    private String name;

    public String getName(){
        return name;
    }

    @SerializedName("status")
    @Expose()
    private String status;

    public String getStatus(){
        return status;
    }

    @SerializedName("species")
    @Expose()
    private String species;

    public String getSpecies(){
        return species;
    }

    @SerializedName("type")
    @Expose()
    private String type;

    public String getType(){
        return type;
    }

    @SerializedName("gender")
    @Expose()
    private String gender;

    public String getGender(){
        return gender;
    }
    @SerializedName("origin")
    @Expose()
    private Origin origin;
    public Origin getOrigin(){
        return origin;
    }

    @SerializedName("location")
    @Expose()
    private Location location;
    public Location getLocationn(){
        return location;
    }

    @SerializedName("image")
    @Expose()
    private String image;
    public String getImage(){
        return image;
    }


}
