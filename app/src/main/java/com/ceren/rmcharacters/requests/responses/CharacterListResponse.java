package com.ceren.rmcharacters.requests.responses;

import com.ceren.rmcharacters.models.ShowCharacter;
import com.ceren.rmcharacters.models.Information;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterListResponse {

    @SerializedName("info")
    private Information info;
    @SerializedName("results")
    private List<ShowCharacter> results;

    public Information getInfo() {
        return info;
    }

    public void setInfo(Information info) {
        this.info = info;
    }

    public List<ShowCharacter> getResults() {
        return results;
    }

    public void setResults(List<ShowCharacter> results) {
        this.results = results;
    }
    //https://rickandmortyapi.com/api/character/


}
