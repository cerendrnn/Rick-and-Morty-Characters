package com.ceren.rmcharacters.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.ceren.rmcharacters.R;
import com.ceren.rmcharacters.util.Constants;

import de.hdodenhof.circleimageview.CircleImageView;

public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView characterName;
    CircleImageView characterImage;
    OnCharacterListener onCharacterListener;
    private Constants c;

    public CharacterViewHolder(@NonNull View itemView, OnCharacterListener onCharacterListener) {
        super(itemView);
        this.onCharacterListener = onCharacterListener;
        characterName = itemView.findViewById(R.id.character_name);
        characterImage = itemView.findViewById(R.id.character_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onCharacterListener.onCharacterClick(getAdapterPosition());
    }
}
