package com.ceren.rmcharacters.requests;

import com.ceren.rmcharacters.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//This class is responsible for instantiating Retrofit instance.
//Retrofit singleton design pattern is used.
//baseUrl method takes the base url of Rick and Morty API. GSON is used for converting JSON into POJO

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());//GSON converter is added.

    private static Retrofit retrofit = retrofitBuilder.build();
    private static CharacterApi characterApi = retrofit.create(CharacterApi.class);

    public static CharacterApi getCharacterApi(){
        return characterApi;
    }//to access Retrofit instance
    //Retrofit object will not be used in the activities API will be used for requests.


}
