package com.ceren.rmcharacters.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ceren.rmcharacters.models.ShowCharacter;
import com.ceren.rmcharacters.repositories.CharacterRepository;

import java.util.List;

//responsible for getting, holding, retrieving character list
// responsible for keeping the list updated
public class CharacterListViewModel extends ViewModel {

    private CharacterRepository mCharacterRepository;
    private boolean mIsViewingRecipes;
    private boolean mIsPerformingQuery;

    public CharacterListViewModel() {
        mCharacterRepository =  CharacterRepository.getInstance();
    }

    public LiveData<List<ShowCharacter>> getCharacters(){
        return mCharacterRepository.getCharacters();
    }

    public void getCharactersApi(int pageNumber){
        mIsViewingRecipes = true;
        mIsPerformingQuery = true;
        mCharacterRepository.getCharactersApi(pageNumber);
    }
}

