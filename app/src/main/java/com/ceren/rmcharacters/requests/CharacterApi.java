package com.ceren.rmcharacters.requests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.ceren.rmcharacters.requests.responses.CharacterListResponse;
import com.ceren.rmcharacters.requests.responses.CharacterResponse;

public interface CharacterApi {

    @GET("character")//get the characters on the first page
    Call<CharacterListResponse> getCharacters(@Query("page") int page);
    @GET("character/{id}")//get the characters on the first page
    Call<CharacterResponse> getCharacter(@Path("id") int characterID);

}
