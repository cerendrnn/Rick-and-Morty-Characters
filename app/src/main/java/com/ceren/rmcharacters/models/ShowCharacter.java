package com.ceren.rmcharacters.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ShowCharacter implements Parcelable {


    @SerializedName("image")
    private String image;
    @SerializedName("location")
    private Location location;
    @SerializedName("origin")
    private Origin origin;
    @SerializedName("gender")
    private String gender;
    @SerializedName("type")
    private String type;
    @SerializedName("species")
    private String species;
    @SerializedName("status")
    private String status;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;

    public ShowCharacter(){
    }
    public ShowCharacter(int id, String name, String status, String species, String type, String gender, Origin origin,
                    Location location, String image) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location= location;
        this.image = image;

    }


    protected ShowCharacter(Parcel in) {

        gender = in.readString();
        type = in.readString();
        species = in.readString();
        status = in.readString();
        name = in.readString();
        id = in.readInt();
    }

    public static final Creator<ShowCharacter> CREATOR = new Creator<ShowCharacter>() {
        @Override
        public ShowCharacter createFromParcel(Parcel in) {
            return new ShowCharacter(in);
        }

        @Override
        public ShowCharacter[] newArray(int size) {
            return new ShowCharacter[size];
        }
    };

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setSpecies(String species){
        this.species = species;
    }
    public String getSpecies(){
        return species;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    public void setOrigin(Origin origin){
        this.origin = origin;
    }
    public Origin getOrigin() {
        return origin;
    }
    public void setLocation(Location location){
        this.location = location;
    }
    public Location getLocationn(){
        return location;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return image;
    }


    @Override
    public String toString() {
        return "ShowCharacter{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", id=" + id +
                ", location=" + location +
                ", origin=" + origin +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        //parcel.writeParcelable(location, i);
        parcel.writeString(gender);
        parcel.writeString(type);
        parcel.writeString(species);
        parcel.writeString(status);
        parcel.writeString(name);
        parcel.writeInt(id);
    }
}

