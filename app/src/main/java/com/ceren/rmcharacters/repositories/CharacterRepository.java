package com.ceren.rmcharacters.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ceren.rmcharacters.models.ShowCharacter;
import com.ceren.rmcharacters.requests.responses.CharacterApiClient;

import java.util.List;
public class CharacterRepository {

    private static CharacterRepository instance;//singleton pattern
    private CharacterApiClient mCharacterApiClient;
    private int mPageNumber;
    private MutableLiveData<Boolean> mIsQueryExhausted = new MutableLiveData<>();


    private CharacterRepository(){
        mCharacterApiClient = CharacterApiClient.getInstance();

    }

    public static CharacterRepository getInstance(){
        if(instance == null){
            instance = new CharacterRepository();
        }
        return instance;
    }
    public LiveData<ShowCharacter> getCharacter(){
        return mCharacterApiClient.getCharacter();
    }


    public LiveData<List<ShowCharacter>> getCharacters(){
        return mCharacterApiClient.getCharacters();
    }

    public void getCharacterById(int characterId){
        mCharacterApiClient.getCharacterById(characterId);
    }

    public void getCharactersApi(int pageNumber){
        if(pageNumber == 0){
            pageNumber = 1;
        }
        mPageNumber = pageNumber;
        //mIsQueryExhausted.setValue(false);
        mCharacterApiClient.getCharactersApi(pageNumber);
    }

}
