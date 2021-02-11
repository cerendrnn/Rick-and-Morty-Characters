package com.ceren.rmcharacters.util;

import android.util.Log;

import com.ceren.rmcharacters.models.ShowCharacter;

import java.util.List;

public class Testing {

    public static void printCharacters(List<ShowCharacter>list, String tag){
        for(ShowCharacter s: list){
            Log.d(tag,"onChanged: "+s.getName() + " "
                    + s.getLocationn() + s.getOrigin());
        }
    }
}
