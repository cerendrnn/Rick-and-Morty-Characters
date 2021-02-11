package com.ceren.rmcharacters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ceren.rmcharacters.adapters.CharacterRecyclerAdapter;
import com.ceren.rmcharacters.adapters.OnCharacterListener;
import com.ceren.rmcharacters.models.ShowCharacter;
import com.ceren.rmcharacters.util.Constants;
import com.ceren.rmcharacters.util.Testing;
import com.ceren.rmcharacters.viewmodels.CharacterListViewModel;

import java.util.List;

public class CharacterListActivity extends AppCompatActivity implements OnCharacterListener {

    private static final String TAG = "CHARACTER LIST ACTIVITY";
    private CharacterListViewModel mCharacterListViewModel;
    private RecyclerView mRecyclerView;
    private CharacterRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_character_list);
        mRecyclerView = findViewById(R.id.character_list);
        mCharacterListViewModel = new ViewModelProvider(this).get(CharacterListViewModel.class);
        initRecyclerView();
        subscribeObservers();
        test();

    }

    private void initRecyclerView(){

        mAdapter = new CharacterRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void test(){
        getCharactersApi(1);
    }

    private void subscribeObservers(){
        mCharacterListViewModel.getCharacters().observe(this, new Observer<List<ShowCharacter>>() {
            @Override
            //onChanged method will be triggered anytime the list changes.
            public void onChanged(List<ShowCharacter> showCharacters) {
                if(showCharacters!=null){
                    Testing.printCharacters(showCharacters, "characters test");
                    mAdapter.setCharacters(showCharacters);
                }

            }
        });
    }


    private void getCharactersApi(int pageNumber)
    {
        mCharacterListViewModel.getCharactersApi(pageNumber);
    }

    @Override
    public void onCharacterClick(int position) {

        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra("character", mAdapter.getSelectedCharacter(position));
        startActivity(intent);

    }
}