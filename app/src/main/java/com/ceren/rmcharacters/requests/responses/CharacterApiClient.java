package com.ceren.rmcharacters.requests.responses;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ceren.rmcharacters.AppExecutors;
import com.ceren.rmcharacters.models.ShowCharacter;
import com.ceren.rmcharacters.requests.ServiceGenerator;
import com.ceren.rmcharacters.util.Constants;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.ceren.rmcharacters.util.Constants.NETWORK_TIMEOUT;
//This class is responsible for retrieving the data from REST API.
//This is the remote data source section in the MVVM.

public class CharacterApiClient {

    private static CharacterApiClient instance;//singleton pattern
    private MutableLiveData<List<ShowCharacter>> mCharacters;
    private MutableLiveData<ShowCharacter> mCharacter;
    private Constants c;
    private RetrieveCharactersRunnable mRetrieveCharactersRunnable;
    private RetrieveCharacterRunnable mRetrieveCharacterRunnable;
    private MutableLiveData<Boolean> mCharacterRequestTimeout = new MutableLiveData<>();

    private static final String TAG = "CharacterApiClient";

    public static CharacterApiClient getInstance(){
        if(instance == null){
            instance = new CharacterApiClient();
        }
        return instance;
    }

    private CharacterApiClient(){
        mCharacters = new MutableLiveData<>();
        mCharacter= new MutableLiveData<>();
    }
    public LiveData<List<ShowCharacter>> getCharacters(){
        return mCharacters;
    }
    public LiveData<ShowCharacter> getCharacter(){
        return mCharacter;
    }

    public void getCharactersApi(int pageNumber){
        if(mRetrieveCharactersRunnable != null){
            mRetrieveCharactersRunnable = null;
        }
        mRetrieveCharactersRunnable = new RetrieveCharactersRunnable(pageNumber);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveCharactersRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }
    public void getCharacterById(int characterId){
        if(mRetrieveCharacterRunnable != null){
            mRetrieveCharacterRunnable = null;
        }
        mRetrieveCharacterRunnable = new RetrieveCharacterRunnable(characterId);

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveCharacterRunnable);

        mCharacterRequestTimeout.setValue(false);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it's timed out
                mCharacterRequestTimeout.postValue(true);
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }
    private class RetrieveCharactersRunnable implements Runnable{

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveCharactersRunnable(int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getCharacters(pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<ShowCharacter> list = new ArrayList<>(((CharacterListResponse)response.body()).getResults());
                    if(pageNumber == 1){
                        mCharacters.postValue(list);
                    }
                    else{
                        List<ShowCharacter> currentCharacters = mCharacters.getValue();
                        currentCharacters.addAll(list);
                        mCharacters.postValue(currentCharacters);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                    mCharacters.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mCharacters.postValue(null);
            }

        }

        private Call<CharacterListResponse> getCharacters(int pageNumber){
            return ServiceGenerator.getCharacterApi().getCharacters(
                    pageNumber);
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
    }

    private class RetrieveCharacterRunnable implements Runnable{

        int id;
        boolean cancelRequest;

        public RetrieveCharacterRunnable(int id) {
            this.id = id;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getCharacter(id).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    ShowCharacter sc = new ShowCharacter(((CharacterResponse)response.body()).getId(),
                            ((CharacterResponse)response.body()).getName(), ((CharacterResponse)response.body()).getStatus(),
                            ((CharacterResponse)response.body()).getSpecies(),((CharacterResponse)response.body()).getType(),
                            ((CharacterResponse)response.body()).getGender(),((CharacterResponse)response.body()).getOrigin(),((CharacterResponse)response.body()).getLocationn(), ((CharacterResponse)response.body()).getImage());
                    mCharacter.postValue(sc);
                }
                else{
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                    mCharacter.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mCharacter.postValue(null);
            }

        }

        private Call<CharacterResponse> getCharacter(int id){
            return ServiceGenerator.getCharacterApi().getCharacter(
                   id);
        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
    }

    public void cancelRequest(){
        if(mRetrieveCharactersRunnable != null){
            mRetrieveCharactersRunnable.cancelRequest();
        }
        if(mRetrieveCharacterRunnable != null){
            mRetrieveCharacterRunnable.cancelRequest();
        }
    }



}
