package com.ceren.rmcharacters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ceren.rmcharacters.models.ShowCharacter;
import com.ceren.rmcharacters.viewmodels.CharacterListViewModel;
import com.ceren.rmcharacters.viewmodels.CharacterViewModel;

public class CharacterActivity extends AppCompatActivity {

    private static final String TAG = "CharacterActivity";

    private AppCompatImageView mCharacterImage;
    private TextView mCharacterTitle;
    private TextView mCharacterGender;
    private TextView mCharacterStatus;
    private TextView mCharacterSpecies;
    private TextView mCharacterOrigin;
    private TextView mCharacterLocation;
    private ScrollView mScrollView;
    private ImageButton ib;
    private CharacterViewModel mCharacterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_character);
        mCharacterImage = findViewById(R.id.character_image);
        mCharacterTitle = findViewById(R.id.character_title);
        mCharacterStatus = findViewById(R.id.character_status);
        mCharacterGender = findViewById(R.id.character_gender);
        mCharacterSpecies = findViewById(R.id.character_species);
        mCharacterOrigin = findViewById(R.id.character_origin);
        mCharacterLocation = findViewById(R.id.character_location);
        ib = findViewById(R.id.backbutton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CharacterListActivity.class);
                view.getContext().startActivity(intent);}
        });

        mCharacterViewModel =new ViewModelProvider(this).get(CharacterViewModel.class);
        subscribeObservers();
        getIncomingIntent();

    }


    private void getIncomingIntent(){
        if(getIntent().hasExtra("character")){
            ShowCharacter character = getIntent().getParcelableExtra("character");
            Log.d(TAG, "getIncomingIntent: " + character.getName());
            mCharacterViewModel.getCharacterById(character.getId());
        }
    }

    private void subscribeObservers(){

        mCharacterViewModel.getCharacter().observe(this, new Observer<ShowCharacter>() {
            @Override
            public void onChanged(ShowCharacter showCharacter) {
                if(showCharacter != null)
                {
                    if(showCharacter.getId()==mCharacterViewModel.getCharacterId()) {
                    Log.d(TAG,"onChanged:............");
                    Log.d(TAG,"onChanged: " + showCharacter.getName());
                    Log.d(TAG,"onChanged: " + showCharacter.getGender());
                        setCharacterProperties(showCharacter);
                    }
                }
            }
        });
    }

    private void setCharacterProperties(ShowCharacter character){
        if(character != null){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(character.getImage())
                    .into(mCharacterImage);

            mCharacterTitle.setText(character.getName());
            mCharacterGender.setText(character.getGender());
            mCharacterStatus.setText(character.getStatus());
            mCharacterSpecies.setText(character.getSpecies());
            mCharacterOrigin.setText(character.getOrigin().getName());
            mCharacterLocation.setText(character.getLocationn().getName());

        }



    }


}