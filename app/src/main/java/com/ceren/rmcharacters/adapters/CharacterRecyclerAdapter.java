package com.ceren.rmcharacters.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ceren.rmcharacters.R;
import com.ceren.rmcharacters.models.ShowCharacter;

import java.util.List;

public class CharacterRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ShowCharacter> mCharacters;
    private OnCharacterListener mOnCharacterListener;

    public CharacterRecyclerAdapter(OnCharacterListener mOnCharacterListener ) {
        this.mOnCharacterListener = mOnCharacterListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_character_list_item, viewGroup,false);
        return new CharacterViewHolder(view,mOnCharacterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);
        Glide.with(viewHolder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(mCharacters.get(position).getImage())
                .into(((CharacterViewHolder)viewHolder).characterImage);
        ((CharacterViewHolder)viewHolder).characterName.setText(mCharacters.get(position).getName());

    }

    public void setCharacters(List<ShowCharacter> characters){
         mCharacters = characters;
         notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCharacters != null) {
            return mCharacters.size();
        }
        return 0;
    }

    public ShowCharacter getSelectedCharacter(int position){
        if(mCharacters != null){
            if(mCharacters.size() > 0){
                return mCharacters.get(position);
            }
        }
        return null;
    }
}