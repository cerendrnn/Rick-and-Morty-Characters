package com.ceren.rmcharacters.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ceren.rmcharacters.models.ShowCharacter;
import com.ceren.rmcharacters.repositories.CharacterRepository;

public class CharacterViewModel extends ViewModel {

    private CharacterRepository mCharacterRepository;
    private int mCharacterId;
    private boolean mDidRetrieveCharacter;

    public CharacterViewModel() {
        mCharacterRepository = CharacterRepository.getInstance();
        mDidRetrieveCharacter = false;
    }

    public LiveData<ShowCharacter> getCharacter(){
        return mCharacterRepository.getCharacter();
    }

    /*public LiveData<Boolean> isCharacterRequestTimedOut(){
        return mCharacterRepository.isCharacterRequestTimedOut();
    }*/

    public void getCharacterById(int characterId){
        mCharacterId = characterId;
        mCharacterRepository.getCharacterById(characterId);
    }

    public int getCharacterId() {
        return mCharacterId;
    }

   
}
